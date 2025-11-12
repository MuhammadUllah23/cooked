import React from "react";
import { useNavigate } from "react-router-dom";
import { StoreResponse } from "../../api/store";
import LoadingScreen from "../common/LoadingScreen";

interface StoreListProps {
  stores: StoreResponse[];
  loading: boolean;
  error: string | null;
}

const StoreList: React.FC<StoreListProps> = ({ stores, loading, error }) => {
  const navigate = useNavigate();

  if (loading) {
    return <LoadingScreen />;
  }

  if (error) {
    return <p className="text-error text-center mt-2">{error}</p>;
  }

  if (stores.length === 0) {
    return <p className="text-text-muted text-center mt-2">No stores found.</p>;
  }

  return (
    <ul className="w-full mt-2">
      {stores.map((store) => (
        <li
          key={store.id}
          onClick={() => navigate(`/stores/${store.id}`)}
          className="border-b border-gray-700 py-2 cursor-pointer hover:text-primary transition-colors"
        >
          {store.name}
        </li>
      ))}
    </ul>
  );
};

export default StoreList;
