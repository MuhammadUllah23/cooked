import React, { FormEvent } from "react";

const RegisterForm: React.FC = () => {
  const handleSubmit = (e: FormEvent<HTMLFormElement>): void => {
    e.preventDefault();
    console.log("Register form submitted");
  };

  return (
    <form onSubmit={handleSubmit} className="flex flex-col space-y-4">
      <input
        type="text"
        placeholder="First Name"
        className="p-3 rounded bg-background border border-blue-400 text-white"
        required
      />
            <input
        type="text"
        placeholder="Last Name"
        className="p-3 rounded bg-background border border-blue-400 text-white"
        required
      />
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
      <input
        type="password"
        placeholder="Confirm Password"
        className="p-3 rounded bg-background border border-blue-400 text-white"
        required
      />
      <button
        type="submit"
        className="bg-primary hover:bg-primary-hover text-white font-bold py-2 rounded transition"
      >
        Register
      </button>
    </form>
  );
};

export default RegisterForm;
