import React from "react";

function HomeNavBar() {
    return (
        <nav className="navbar">
            <div className="logo">Cooked</div>
            <div className="nav-links">
                <a href="/login">Login</a>
                <a href="/register">Register</a>
            </div>
        </nav>
    )
}

export default HomeNavBar;