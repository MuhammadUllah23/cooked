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
      <div className="flex flex-col">
        <label htmlFor="email" className="text-white mb-1">Email</label>
        <input
          id="email"
          type="email"
          placeholder="Enter your email"
          className="p-3 rounded bg-background border border-blue-400 text-white"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
      </div>

      <div className="flex flex-col">
        <label htmlFor="password" className="text-white mb-1">Password</label>
        <input
          id="password"
          type="password"
          placeholder="Enter your password"
          className="p-3 rounded bg-background border border-blue-400 text-white"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
      </div>
      
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
