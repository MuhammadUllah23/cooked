import { useCallback } from "react";
import { useNavigate } from "react-router-dom";
import { logoutUser } from "../api/auth";
import { useAuth } from "../context/AuthContext"; 
import { LogoutRequest } from "../api/auth"; 

export const useLogoutHandler = () => {
  const navigate = useNavigate();
  const { logout, user } = useAuth();

  const handleLogout = useCallback(async () => {
    try {
      const logoutData: LogoutRequest = {
        deviceId: localStorage.getItem("deviceId") ?? ""
      };

      await logoutUser(logoutData); 
      logout();
      navigate("/auth?mode=login", { replace: true }); 
    } catch (error) {
      console.error("Logout failed:", error);
    }
  }, [logout, navigate, user]);

  return { handleLogout };
};
