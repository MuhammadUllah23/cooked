import React, { FormEvent, useState } from "react";
import { useLoginHandler } from "../utils/handleLogin";
import { useNavigate } from "react-router-dom";

import ErrorMessage from "./ErrorMessage";

const LoginForm: React.FC = () => {

  const { handleLogin, loading, error } = useLoginHandler();
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    try {
      const loginData = { email, password };
      const authData = await handleLogin(loginData);

      if (authData?.user?.id) {
        navigate(`${authData.user.id}/business/create/`);
      }
    } catch (err) {
      console.error("Login failed:", err);
    }
  };


  return (
    <form onSubmit={handleSubmit} className="flex flex-col space-y-4">
      {error && <ErrorMessage message={error} />}
      <input
        type="email"
        placeholder="Email"
        className="p-3 rounded bg-background border border-blue-400 text-white"
        required
      />
      <input
        type="password"
        placeholder="Password"
        className="p-3 rounded bg-background border border-blue-400 text-white"
        required
      />
      <button
        type="submit"
        className="bg-primary hover:bg-primary-hover text-white font-bold py-2 rounded transition"
      >
        {loading ? "Logging in..." : "Login"}
      </button>
    </form>
  );
};

export default LoginForm;
