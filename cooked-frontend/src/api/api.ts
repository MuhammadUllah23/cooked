import axios, { AxiosError, AxiosInstance, AxiosRequestConfig, AxiosHeaders } from "axios";
import { callLogout } from "../context/AuthContext";

let accessToken: string | null = null;

const API_BASE_URL = import.meta.env.VITE_API_URL || "http://localhost:8080/api";

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
  headers: AxiosHeaders;
}

// ----- Robust queue pattern -----
interface FailedRequest {
  resolve: (value: any) => void;
  reject: (reason?: any) => void;
  originalRequest: AxiosRequestConfigWithRetry;
}

let isRefreshing = false;
let failedQueue: FailedRequest[] = [];

const processQueue = (error: any, token: string | null = null) => {
  failedQueue.forEach(({ resolve, reject, originalRequest }) => {
    if (token) {
      originalRequest.headers.set("Authorization", `Bearer ${token}`);
      resolve(api(originalRequest));
    } else {
      reject(error);
    }
  });
  failedQueue = [];
};

// Request interceptor → attach access token
api.interceptors.request.use((config) => {
  config.headers = config.headers ?? new AxiosHeaders();

  if (accessToken) {
    config.headers.set("Authorization", `Bearer ${accessToken}`);
  }

  return config;
});

// Response interceptor → handle 401 refresh logic
api.interceptors.response.use(
  (response) => response,
  async (error: AxiosError) => {
    const originalRequest = error.config as AxiosRequestConfigWithRetry;

    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      if (isRefreshing) {
        // Queue this request until refresh completes
        return new Promise((resolve, reject) => {
          failedQueue.push({ resolve, reject, originalRequest });
        });
      }

      isRefreshing = true;

      try {
        const deviceId = localStorage.getItem("deviceId") || "";
        const refreshResponse = await api.post(
          "/auth/refresh",
          { deviceId },
          { withCredentials: true }
        );

        const newAccessToken = (refreshResponse.data as { accessToken: string }).accessToken;

        setAccessToken(newAccessToken);

        // Retry all queued requests
        processQueue(null, newAccessToken);

        // Retry original request
        originalRequest.headers.set("Authorization", `Bearer ${newAccessToken}`);
        return api(originalRequest);
      } catch (refreshError) {
        // Refresh failed reject queued requests and logout
        processQueue(refreshError, null);
        callLogout();
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
