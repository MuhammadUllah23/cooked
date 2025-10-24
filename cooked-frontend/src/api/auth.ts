import api, { setAccessToken } from "./api";

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
  deviceId: string;
}

export interface RefreshRequest {
  deviceId: string;
}

export interface RefreshResponse {
  accessToken: string;
}

export async function registerUser(data: RegisterRequest): Promise<AuthResponse> {

}

export async function loginUser(data: LoginRequest): Promise<AuthResponse> {

}

// export async function logoutUser(data: RegisterRequest): Promise<AuthResponse> {

// }

export async function refresh(data: RefreshRequest): Promise<RefreshResponse> {

}