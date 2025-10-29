import React, { useState } from "react";

interface ErrorMessageProps {
  message: string;
}

const ErrorMessage: React.FC<ErrorMessageProps> = ({ message }) => {
  const [visible, setVisible] = useState(true);

  if (!visible) return null;

  return (
    <div className="w-full flex justify-between items-center border border-error bg-error/20 text-error font-medium px-4 py-2 rounded-md">
      <span className="text-sm sm:text-base md:text-lg">{message}</span>
      <button
        onClick={() => setVisible(false)}
        className="ml-4 font-bold hover:text-error-dark transition"
      >
        ×
      </button>
    </div>
  );
};

export default ErrorMessage;
