import React from "react";
import { useLogoutHandler } from "../../hooks/authHandlers/useLogoutHandler"; // adjust path

interface LogoutButtonProps {
  className?: string; // optional for flexibility (e.g., custom placement)
}

const LogoutButton: React.FC<LogoutButtonProps> = () => {
  const { handleLogout } = useLogoutHandler();

  return (
    <button
      onClick={handleLogout}
      className={`text-lg text-btn-secondary hover:text-white transition-colors`}
    >
      Logout
    </button>
  );
};

export default LogoutButton;
