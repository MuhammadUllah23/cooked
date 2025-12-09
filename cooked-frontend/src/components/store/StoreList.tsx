import React from "react";
import { useNavigate } from "react-router-dom";
import { Store, StoreResponse } from "../../api/store";
import LoadingScreen from "../common/LoadingScreen";
import { Trash2 } from "lucide-react";

interface StoreListProps {
  stores: StoreResponse[];
  loading: boolean;
  error: string | null;
  onEdit: (store: Store) => void;
  onDelete: (id: string) => void; 
}

const StoreList: React.FC<StoreListProps> = ({ stores, loading, error, onEdit, onDelete }) => {
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
          className="flex justify-between items-center border-b border-gray-700 py-2"
        >
          <span
            onClick={() => navigate(`/stores/${store.id}`)}
            className="cursor-pointer hover:text-primary transition-colors"
          >
            {store.name}
          </span>

          <button
            onClick={() => onEdit(store)}
            className="text-sm bg-primary px-3 py-1 rounded hover:bg-primary-hover transition"
          >
            Edit
          </button>
          <button
            onClick={() => onDelete(store.id)}
            className="p-2 rounded hover:error-dark transition"
          >
            <Trash2 size={18} className="text-error" />
          </button>
        </li>
      ))}
    </ul>
  );
};

export default StoreList;
