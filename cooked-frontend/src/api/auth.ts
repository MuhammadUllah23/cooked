import api, { clearAccessToken, getAccessToken, setAccessToken } from "./api";
import { handleApiError } from "./util/errorHandler";

export enum UserSubscription {
  FREE = "FREE",
  PREMIUM = "PREMIUM",
}

export enum UserRole {
  USER = "USER",
}

export interface UserResponse {
  id: string;
  email: string;
  firstName: string;
  lastName: string;
  role: UserRole;
  subscription: UserSubscription;
}

export interface AuthResponse {
  token: string;
  user: UserResponse;
  deviceId: string;
}

export interface RegisterRequest {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
}

export interface LoginRequest {
  email: string;
  password: string;
  deviceId?: string;
}

export interface RefreshRequest {
  deviceId: string;
}

export interface RefreshResponse {
  accessToken: string;
}

export interface LogoutRequest {
  deviceId: string;
  global?: boolean;
}

export async function registerUser(data: RegisterRequest): Promise<AuthResponse> {
  try {
    const response = await api.post<AuthResponse>("/auth/register", data, {
      withCredentials: true,
    })
    const authData = response.data;
    setAccessToken(authData.token)
    return authData
  } catch (error) {
    handleApiError(error);
  }

}

export async function loginUser(data: LoginRequest): Promise<AuthResponse> {
  try {
    const response = await api.post<AuthResponse>("/auth/login", data, {
      withCredentials: true,
    })

    const authData = response.data;
    setAccessToken(authData.token)
    return authData
  } catch (error) {
    handleApiError(error);
  }
}

export async function logoutUser(data: LogoutRequest): Promise<void> {
  try {
    await api.post("/auth/logout", data, {
      withCredentials: true,
      headers: {
        Authorization: `Bearer ${getAccessToken()}`,
      },
    });
    clearAccessToken();
  } catch (error) {
    handleApiError(error);
  }

}

export async function refresh(data: RefreshRequest): Promise<RefreshResponse> {
  try {
    const response = await api.post("/auth/refresh", data, {
      withCredentials: true,
    });

    const newToken = response.data.accessToken
    setAccessToken(newToken)

    return newToken
  } catch (error) {
    handleApiError(error);
  }

}