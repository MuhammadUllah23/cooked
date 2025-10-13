import React from "react";

function HomeNavBar() {
  return (
    <nav className="flex justify-between items-center bg-navbar text-white py-4 px-8 shadow-md">
      <div className="text-2xl font-bold tracking-tight">
        Cooked
      </div>

      <div className="flex space-x-6">
        <a
          href="/auth?mode=login"
          className="text-blue-400 font-medium hover:text-white hover:underline transition-colors"
        >
          Login
        </a>
        <a
          href="/auth?mode=register"
          className="text-blue-400 font-medium hover:text-white hover:underline transition-colors"
        >
          Register
        </a>
      </div>
    </nav>
  );
}

export default HomeNavBar;
