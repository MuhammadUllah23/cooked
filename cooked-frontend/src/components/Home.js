import React from "react";
import '../css/Home.css';

import HomeNavBar from "./HomeNavBar";

function Home() {
    return (
        <div>
            <HomeNavBar />
            <div className="home">
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
        </div>
    );
}

export default Home;