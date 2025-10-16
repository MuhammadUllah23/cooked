import React, { FormEvent } from "react";

const LoginForm: React.FC = () => {
  const handleSubmit = (e: FormEvent<HTMLFormElement>): void => {
    e.preventDefault();
    console.log("Login form submitted");
  };

  return (
    <form onSubmit={handleSubmit} className="flex flex-col space-y-4">
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
        Login
      </button>
    </form>
  );
};

export default LoginForm;
