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

// Request interceptor → attach access token if available
api.interceptors.request.use((config) => {
  if (accessToken) {
    config.headers.Authorization = `Bearer ${accessToken}`;
  }
  return config;
});

// Response interceptor handle 401 refresh logic
api.interceptors.response.use(
  (response) => response, 
  async (error: AxiosError) => {
    const originalRequest = error.config as AxiosRequestConfig & { _retry?: boolean };

    // If request failed with 401 and it hasn’t been retried yet → try refresh
    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;
      try {
        const refreshResponse = await axios.post(
          `${API_BASE_URL}/auth/refresh`,
          {},
          { withCredentials: true }
        );

        const newAccessToken = (refreshResponse.data as { accessToken: string }).accessToken;
        accessToken = newAccessToken; 

        // Retry original request with new token
        if (originalRequest.headers) {
          originalRequest.headers.Authorization = `Bearer ${newAccessToken}`;
        }

        // retry the request
        return api(originalRequest); 
      } catch (refreshError) {
        console.error("Token refresh failed:", refreshError);
        window.location.href = "/login";
      }
    }

    return Promise.reject(error);
  }
);

export const setAccessToken = (token: string) => {
  accessToken = token;
};

export const clearAccessToken = () => {
  accessToken = null;
};

export default api;
