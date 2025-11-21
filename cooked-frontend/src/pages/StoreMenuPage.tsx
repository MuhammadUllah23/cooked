import { useParams, useNavigate } from "react-router-dom";
import LogoutButton from "../components/common/LogoutButton";
import { CirclePlus } from "lucide-react";
import { useEffect, useState } from "react";
import StoreForm from "../components/forms/storeForm";
import { useGetStoresByUserHandler, useCreateStoreHandler } from "../hooks/useStoresHandlers";
import StoreList from "../components/store/StoreList";

const StoreMenuPage = () => {
  const { userId } = useParams();
  
  const [showStoreForm, setShowStoreForm] = useState(false);

  const { stores, handleGetStoresByUser, loading, error, setStores } = useGetStoresByUserHandler();


  useEffect(() => {
    if (userId) handleGetStoresByUser(userId);
  }, [userId]);

  const handleAddStoreClick = () => {
    setShowStoreForm(true);
  };

  return (
    <div className="min-h-screen bg-background text-white flex flex-col items-center justify-center px-4">
      <div className="bg-navbar rounded-2xl p-8 w-full max-w-md shadow-lg flex flex-col items-center">
        <div>
          <h1 className="text-3xl font-bold text-center mb-6">
            Stores
          </h1>
          <StoreList stores={stores} loading={loading} error={error} />
        </div>

        {/* Add Store Button */}
        <div className="w-full flex flex-col items-center mt-4">
          {!showStoreForm ? (
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
              onCancel={() => setShowStoreForm(false)}
              setStores={setStores}
              mode="create"
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