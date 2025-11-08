import React from "react";
import HomeNavBar from "../components/HomeNavBar.js";

function Home() {
  return (
    <div className="bg-background min-h-screen font-sans text-text flex flex-col">
      <HomeNavBar />

      <main className="flex flex-col items-center justify-center flex-1 text-center px-6">
        <h1 className="text-6xl font-extrabold tracking-tight mb-4">
          Simplify Your Inventory
        </h1>
        <h2 className="text-2xl font-medium text-text-muted mb-6">
          Manage your business inventory with ease
        </h2>
        <p className="text-lg text-text-muted max-w-xl mb-8">
          Cooked helps small businesses track, manage, and organize their stock
          effortlessly — saving you time and preventing costly mistakes.
        </p>
        <a
          href="/register"
          className="bg-primary hover:bg-primary-hover text-text font-semibold py-3 px-8 rounded-lg shadow-md transition-all duration-200 hover:shadow-lg"
        >
          Get Started
        </a>
      </main>

      <footer className="text-text-muted text-center py-4 text-sm border-t border-navbar">
        &copy; {new Date().getFullYear()} Cooked. All rights reserved.
      </footer>
    </div>
  );
}

export default Home;
