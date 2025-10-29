import React from "react";
import { useSearchParams, useNavigate } from "react-router-dom";
import LoginForm from "../components/LoginForm";
import RegisterForm from "../components/RegisterForm";

const AuthPage: React.FC = () => {
  const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams();
  const mode = (searchParams.get("mode") || "login") as "login" | "register";

  const toggleMode = (): void => {
    setSearchParams({ mode: mode === "login" ? "register" : "login" });
  };

  return (
    <div className="min-h-screen bg-background text-white flex flex-col items-center justify-center px-4">
      <div className="bg-navbar rounded-2xl p-8 w-full max-w-md shadow-lg">
        <h1 className="text-3xl font-bold text-center mb-6">
          {mode === "login" ? "Welcome Back" : "Create Account"}
        </h1>

        {mode === "login" ? <LoginForm /> : <RegisterForm />}

        <button
          onClick={toggleMode}
          className="mt-6 w-full text-blue-400 hover:text-blue-200 transition-colors text-sm"
        >
          {mode === "login"
            ? "Don’t have an account? Register"
            : "Already have an account? Login"}
        </button>
      </div>
      <div className="mt-4 flex justify-center">
        <button
          onClick={() => navigate("/")}
          className="text-sm text-btn-secondary hover:text-btn-secondary-hover transition-colors"
        >
          ← Back to Home
        </button>
      </div>
    </div>
  );
};

export default AuthPage;
