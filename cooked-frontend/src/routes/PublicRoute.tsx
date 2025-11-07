import React from "react";
import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

const PublicRoute = () => {
  const { isLoggedIn, user } = useAuth();

  // If already logged in, redirect them away from /auth
  if (isLoggedIn && user) {
    return <Navigate to={`/${user.id}/business/create`} replace />;
  }

  return <Outlet />;
};

export default PublicRoute;
