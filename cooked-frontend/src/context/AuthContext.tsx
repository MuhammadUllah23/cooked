import React, { createContext, useContext, useState, useEffect, ReactNode } from "react";
import { UserResponse } from "../api/auth";
import { setAccessToken } from "../api/api";
import { refresh } from "../api/auth";

interface AuthContextType {
    user: UserResponse | null;
    accessToken: string | null;
    deviceId: string | null;
    login: (user: UserResponse, token: string, deviceId: string) => void;
    logout: () => void;
    isLoggedIn: boolean;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [user, setUser] = useState<UserResponse | null>(null);
    const [accessToken, setToken] = useState<string | null>(null);
    const [deviceId, setDeviceId] = useState<string | null>(null);

      useEffect(() => {
    const storedUser = localStorage.getItem("user");
    const storedDeviceId = localStorage.getItem("deviceId");

    if (storedUser && storedDeviceId) {
      const parsedUser = JSON.parse(storedUser);
      setUser(parsedUser);
      setDeviceId(storedDeviceId);

      // Try refreshing access token via cookie
      refresh()
        .then((newToken) => {
          setToken(newToken);
          setAccessToken(newToken);
        })
        .catch(() => {
          logout(); // refresh failed → user not authenticated
        });
    }
  }, []);
}