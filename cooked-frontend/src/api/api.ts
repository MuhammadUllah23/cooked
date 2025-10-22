import axios, { AxiosError, AxiosInstance, AxiosRequestConfig } from "axios";

let accessToken: string | null = null;

const API_BASE_URL = import.meta.env.VITE_API_URL;

const api: AxiosInstance = axios.create({
  baseURL: API_BASE_URL,
  withCredentials: true,
  headers: {
    "Content-Type": "application/json",
  },
});

// Type for Axios config with optional _retry
interface AxiosRequestConfigWithRetry extends AxiosRequestConfig {
  _retry?: boolean;
}

// Request interceptor → attach access token if available
api.interceptors.request.use((config) => {
  if (accessToken) {
    config.headers = config.headers ?? {}; // ensure headers exist

    // If AxiosHeaders object, use set()
    if ('set' in config.headers && typeof config.headers.set === 'function') {
      config.headers.set('Authorization', `Bearer ${accessToken}`);
    } else {
      // fallback for plain object
      (config.headers as any).Authorization = `Bearer ${accessToken}`;
    }
  }
  return config;
});

// Response interceptor → handle 401 refresh logic
let isRefreshing = false;
let failedQueue: Array<(token: string | null) => void> = [];

api.interceptors.response.use(
  (response) => response,
  async (error: AxiosError) => {
    const originalRequest = error.config as AxiosRequestConfigWithRetry;

    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      if (isRefreshing) {
        // Queue failed requests until refresh completes
        return new Promise((resolve) => {
          failedQueue.push((token) => {
            if (token) {
              originalRequest.headers = {
                ...originalRequest.headers,
                Authorization: `Bearer ${token}`,
              };
            }
            resolve(api(originalRequest));
          });
        });
      }

      isRefreshing = true;

      try {
        // Call refresh endpoint
        const refreshResponse = await api.post("/v1/auth/refresh", {});
        const newAccessToken = (refreshResponse.data as { accessToken: string }).accessToken;

        // Update in-memory token
        setAccessToken(newAccessToken);

        // Retry all queued requests
        failedQueue.forEach((cb) => cb(newAccessToken));
        failedQueue = [];

        // Retry original request with new token
        originalRequest.headers = {
          ...originalRequest.headers,
          Authorization: `Bearer ${newAccessToken}`,
        };
        return api(originalRequest);
      } catch (refreshError) {
        // Refresh failed → logout
        failedQueue.forEach((cb) => cb(null));
        failedQueue = [];
        setAccessToken(null);

        window.localStorage.removeItem("user");
        window.localStorage.removeItem("deviceId");
        window.location.href = "/login";

        return Promise.reject(refreshError);
      } finally {
        isRefreshing = false;
      }
    }

    return Promise.reject(error);
  }
);

// Export helper functions to manage token
export const setAccessToken = (token: string | null) => {
  accessToken = token;
};

export const clearAccessToken = () => {
  accessToken = null;
};

export default api;
