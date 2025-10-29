import { LoginRequest } from "../api/auth";
import { loginUser } from "../api/auth";
import { useAuth } from "../context/AuthContext";
import { useState } from "react";

export function useLoginHandler() {
  const { login } = useAuth();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleLogin = async (credentials: Omit<LoginRequest, "deviceId">) => {
    setLoading(true);
    setError(null);

    try {
      let deviceId = localStorage.getItem("deviceId") || undefined;

      const authData = await loginUser({
        ...credentials,
        deviceId, 
      });

      if (authData) {
        login(authData.user, authData.token, authData.deviceId);
      }
    } catch (err) {
      const message = (err as Error).message || "Login failed. Please try again.";
      setError(message);
    } finally {
      setLoading(false);
    }
  };

  return { handleLogin, loading, error };
}