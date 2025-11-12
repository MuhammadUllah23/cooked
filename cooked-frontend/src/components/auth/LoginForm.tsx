import React, { FormEvent, useState } from "react";
import { useLoginHandler } from "../../hooks/authHandlers/useLoginHandler";
import { useNavigate } from "react-router-dom";

import ErrorMessage from "../common/ErrorMessage";
import PasswordInput from "./PasswordInput";

const LoginForm: React.FC = () => {

  const { handleLogin, loading, error } = useLoginHandler();
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const loginData = { email, password };
    const authData = await handleLogin(loginData);

    if (authData?.user?.id) {
      navigate(`/${authData.user.id}/stores/`);
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
          className="p-3 rounded bg-background border border-btn-primary text-white"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
      </div>
      <PasswordInput
        id="password"
        label="Password"
        placeholder="Enter your password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
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
