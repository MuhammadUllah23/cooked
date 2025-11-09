import { LoginRequest, RegisterRequest } from "../api/auth";
import { loginUser } from "../api/auth";
import { useAuth } from "../context/AuthContext";
import { useState } from "react";

export function useRegistrationHandler() {
  const { login } = useAuth();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleRegistration = async (credentials: RegisterRequest) => {
    setLoading(true);
    setError(null);

    try {
      const authData = await loginUser({
        ...credentials
      });

      if (authData) {
        login(authData.user, authData.token, authData.deviceId);
        return authData;
      }
    } catch (err) {
      const message = (err as Error).message || "Registration failed. Please try again.";
      setError(message);
    } finally {
      setLoading(false);
    }
  };

  return { handleRegistration, loading, error, setError };
}