import React, { createContext, useContext, useState, useEffect, ReactNode } from "react";
import { UserResponse } from "../api/auth";
import { setAccessToken } from "../api/api";
import { refresh, logoutUser } from "../api/auth";

interface AuthContextType {
    user: UserResponse | null;
    accessToken: string | null;
    deviceId: string | null;
    login: (user: UserResponse, token: string, deviceId: string) => void;
    logout: () => void;
    isLoggedIn: boolean;
}

let logoutSingleton: (() => void) | null = null; 

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [user, setUser] = useState<UserResponse | null>(null);
    const [deviceId, setDeviceId] = useState<string | null>(null);

  useEffect(() => {
    const storedUser = localStorage.getItem("user");
    const storedDeviceId = localStorage.getItem("deviceId");

    if (storedUser && storedDeviceId) {
      const parsedUser = JSON.parse(storedUser);
      setUser(parsedUser);
      setDeviceId(storedDeviceId);      

      // Try refreshing access token via cookie
      refresh({deviceId: storedDeviceId})
        .then((res) => {
          setAccessToken(res.accessToken);
        })
        .catch((error) => {
          logout(); 
        });
    }
  }, []);

  const login = (user: UserResponse, token: string, deviceId: string) => {
    setUser(user);
    setDeviceId(deviceId);
    localStorage.setItem("user", JSON.stringify(user));
    localStorage.setItem("deviceId", deviceId);
    setAccessToken(token);
  };

  const logout = () => {
    setUser(null);
    setDeviceId(null);
    localStorage.removeItem("user");
    localStorage.removeItem("deviceId");
    setAccessToken(null);
    window.location.href = "/auth";
  };

  logoutSingleton = logout;

  const value = {
    user,
    deviceId,
    accessToken: "",
    login,
    logout,
    isLoggedIn: !!user,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};

export const callLogout = () => {
  if (logoutSingleton) logoutSingleton();
};