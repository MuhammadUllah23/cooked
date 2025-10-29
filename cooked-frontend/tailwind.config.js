/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}", // scans all React components
    "./public/index.html"
  ],
  theme: {
    extend: {
      colors: {
        background: "#2a3f57",        // --color-background
        navbar: "#1e2e42",            // --color-navbar
        primary: "#4da6ff",           // --color-primary
        "primary-hover": "#3399ff",   // --color-primary-hover
        text: "#ffffff",              // --color-text
        "text-muted": "#d3d3d3",     // --color-text-muted
        error: "#EF4444", // bright red
        "error-dark": "#B91C1C", // darker variant,
        "btn-primary": "#60A5FA",          // blue-400
        "btn-secondary": "#9CA3AF", // gray-400 equivalent for secondary buttons
      }
    },
  },
  plugins: [],
}

