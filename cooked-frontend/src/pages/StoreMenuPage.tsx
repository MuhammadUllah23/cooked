import { useParams, useNavigate } from "react-router-dom";
import LogoutButton from "../components/common/LogoutButton";
import { CirclePlus } from "lucide-react";
import { useState } from "react";
import StoreForm from "../components/forms/storeForm";

const StoreMenuPage = () => {
  const { userId } = useParams();
  const navigate = useNavigate();
  
  const [showForm, setShowForm] = useState(false);
  const [storeName, setStoreName] = useState("");

  const handleAddStoreClick = () => {
    setShowForm(true);
  };

  const handleCreateStore = async (storeName: string) => {
    console.log("New store for user", userId, ":", storeName);
    // TODO: call API to create the store
    setShowForm(false);
  };

  return (
    <div className="min-h-screen bg-background text-white flex flex-col items-center justify-center px-4">
      <div className="bg-navbar rounded-2xl p-8 w-full max-w-md shadow-lg flex flex-col items-center">
        <div>
          <h1 className="text-3xl font-bold text-center mb-6">
            Stores
          </h1>
        </div>

        {/* Add Store Button */}
        <div className="w-full flex flex-col items-center mt-4">
          {!showForm ? (
            <button
              onClick={handleAddStoreClick}
              className="flex items-center gap-2 border border-white rounded-full px-4 py-2 text-btn-primary bg-background hover:bg-primary-hover hover:text-white transition-colors"
            >
              <CirclePlus className="w-5 h-5" />
              Add Store
            </button>
          ) : (
            <StoreForm
              userId={userId}
              onCancel={() => setShowForm(false)}
              onCreate={handleCreateStore}
            />
            )}
          </div>
      </div>
      <div className="mt-4 flex justify-center">
        <LogoutButton />
      </div>
    </div>
  );
};

export default StoreMenuPage;