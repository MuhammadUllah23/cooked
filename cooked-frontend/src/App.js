import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from './pages/Home';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        {/* <Route path="/auth" element={<Auth />} /> */}
        <Route path="*" element={<h1>404 Page Not Found</h1>} />
      </Routes>
    </Router>
  );
}

export default App;
