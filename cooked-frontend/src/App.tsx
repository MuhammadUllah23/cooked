import React from "react";
import { useAuth } from "./context/AuthContext";
import LoadingScreen from "./components/LoadingScreen";
import AppRouter from "./routes/AppRouter";

const App: React.FC = () => {
  const { isLoading } = useAuth();

  if (isLoading) {
    return <LoadingScreen />;
  }

  return <AppRouter />;
};

export default App;
