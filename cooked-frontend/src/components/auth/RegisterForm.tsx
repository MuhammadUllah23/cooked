import React, { FormEvent, useState } from "react";
import PasswordInput from "./PasswordInput";
import { useRegistrationHandler } from "../../hooks/authHandlers/useRegistrationHandler";
import ErrorMessage from "../common/ErrorMessage";
import { useNavigate } from "react-router-dom";

const RegisterForm: React.FC = () => {
  const { handleRegistration, loading, error, setError } = useRegistrationHandler();
  const navigate = useNavigate();

  const [validationError, setValidationError] = useState<string | null>(null);

  
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  // Password validation checks
  const hasUpperCase = /[A-Z]/.test(password);
  const hasLowerCase = /[a-z]/.test(password);
  const hasNumber = /[0-9]/.test(password);
  const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
  const hasMinMaxLength = password.length >= 8 && password.length <= 255;

  const isPasswordValid =
    hasUpperCase && hasLowerCase && hasNumber && hasSpecialChar && hasMinMaxLength;
  
  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setError(null);
    const validation = validateRegistration({
      firstName,
      lastName,
      email,
      password,
      confirmPassword,
    });

    if (validation) {
      setValidationError(validation);
      return;
    }
    if (!isPasswordValid) {
      setValidationError("Password does not meet the required criteria.");
      return;
    }

    setValidationError(null);
    
    const registrationData = { firstName, lastName, email, password };
    const authData = await handleRegistration(registrationData);
    if (authData?.user?.id) {
      navigate(`/${authData.user.id}/stores/`);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="flex flex-col space-y-4">
      {(validationError || error) && (
        <ErrorMessage message={validationError || error!} />
      )}
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

      <div>
        <ul className="text-sm mb-2 space-y-1">
          <li className={hasUpperCase ? "text-green-600" : "text-red-500"}>
            • At least one uppercase letter
          </li>
          <li className={hasLowerCase ? "text-green-600" : "text-red-500"}>
            • At least one lowercase letter
          </li>
          <li className={hasNumber ? "text-green-600" : "text-red-500"}>
            • At least one number
          </li>
          <li className={hasSpecialChar ? "text-green-600" : "text-red-500"}>
            • At least one special character (!@#$%^&* etc.)
          </li>
          <li className={hasMinMaxLength ? "text-green-600" : "text-red-500"}>
            • Minimum 8 characters
          </li>
        </ul>
      </div>

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
