import React from "react";
import { useLogoutHandler } from "../utils/handleLogout"; // adjust path

interface LogoutButtonProps {
  className?: string; // optional for flexibility (e.g., custom placement)
}

const LogoutButton: React.FC<LogoutButtonProps> = () => {
  const { handleLogout } = useLogoutHandler();

  return (
    <button
      onClick={handleLogout}
      className={`text-sm text-btn-secondary hover:text-white transition-colors`}
    >
      Logout
    </button>
  );
};

export default LogoutButton;
