import React, { FormEvent, useState } from "react";
import PasswordInput from "./PasswordInput";
import { useRegistrationHandler } from "../utils/handleRegistration";
import ErrorMessage from "./ErrorMessage";

const RegisterForm: React.FC = () => {
  const { handleRegistration, loading, error, setError } = useRegistrationHandler();

  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  
  const handleSubmit = (e: FormEvent<HTMLFormElement>): void => {
    e.preventDefault();
    if (password !== confirmPassword) {
        setError("Passwords do not match!");
      return;
    }
    
    console.log("Register form submitted");
  };

  return (
    <form onSubmit={handleSubmit} className="flex flex-col space-y-4">
      {error && <ErrorMessage message={error} />}
      <div className="flex flex-col">
        <label htmlFor="firstName" className="text-white mb-1">First Name</label>
        <input
          id="firstName"
          type="firstName"
          placeholder="Enter your first name"
          className="p-3 rounded bg-background border border-btn-primary text-white"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
          required
        />
      </div>
      <div className="flex flex-col">
        <label htmlFor="lastName" className="text-white mb-1">Last Name</label>
        <input
          id="lastName"
          type="text"
          placeholder="Enter your last name"
          className="p-3 rounded bg-background border border-btn-primary text-white"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
          required
        />
      </div>

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
      <PasswordInput
        id="confirmPassword"
        label="Confirm Password"
        placeholder="Re-enter your password"
        value={confirmPassword}
        onChange={(e) => setConfirmPassword(e.target.value)}
        required
      />
      <button
        type="submit"
        className="bg-primary hover:bg-primary-hover text-white font-bold py-2 rounded transition"
      >
        {loading ? "Logging in..." : "Register"}
      </button>
    </form>
  );
};

export default RegisterForm;
