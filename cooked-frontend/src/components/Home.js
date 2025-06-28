import React from "react";
import '../css/Home.css';

function Home() {
    return (
        <div className="home">
        <header className="header">
            <h1>Cooked</h1>
            <nav>
            <a href="/login">Login</a>
            </nav>
        </header>

        <main className="hero">
        <h1>hi</h1>
            <h2>Manage your small business inventory with ease</h2>
            <p>Cooked helps small businesses track, manage, and organize their stock effortlessly.</p>
            <a className="cta-button" href="/register">Get Started</a>
        </main>

        <footer>
            &copy; {new Date().getFullYear()} Cooked
        </footer>
        </div>
    );
}

export default Home;