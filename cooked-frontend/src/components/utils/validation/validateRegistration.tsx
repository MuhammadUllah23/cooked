const validateRegistration = ({
  firstName,
  lastName,
  email,
  password,
  confirmPassword,
}: {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  confirmPassword: string;
}): string | null => {
  
  if (!firstName.trim() || !lastName.trim() || !email.trim()) {
    return "All fields are required.";
  }

  if (firstName.length > 100) return "First name cannot exceed 100 characters.";
  if (lastName.length > 100) return "Last name cannot exceed 100 characters.";
  if (email.length > 255) return "Email cannot exceed 255 characters.";

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email)) return "Enter a valid email address.";

  if (password !== confirmPassword) return "Passwords do not match.";

  return null; 
};
