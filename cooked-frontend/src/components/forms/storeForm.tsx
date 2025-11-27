import { useState } from "react";
import { useCreateStoreHandler, useUpdateStoreHandler } from "../../hooks/useStoresHandlers";
import { Store, StoreResponse } from "../../api/store";
import ErrorMessage from "../common/ErrorMessage";

interface StoreFormProps {
  userId?: string;
  initialName?: string;
  mode?: "create" | "edit";
  onCancel: () => void;
  setStores: React.Dispatch<React.SetStateAction<StoreResponse[]>>;
  store?: Store | null;
}

const StoreForm: React.FC<StoreFormProps> = ({
  userId,
  initialName = "",
  mode = "create",
  onCancel,
  setStores,
  store,
}) => {

  const [storeName, setStoreName] = useState(initialName);
  const [validationError, setValidationError] = useState<string | null>(null);

  const buttonLabel = mode === "edit" ? "Save" : "Create";

  const { handleCreateStore, error: createError } = useCreateStoreHandler();
  const { handleUpdateStore, error: updateError } = useUpdateStoreHandler();

  const error = mode === "edit" ? updateError : createError;

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!userId) return;

    if (storeName.trim() === "") {
      setValidationError("Store name cannot be blank.");
      return;
    }

    if (storeName.length > 100) {
      setValidationError("Store name cannot exceed 100 characters.");
      return;
    }

    setValidationError(null);

    if (mode == "create") {
      const newStore = await handleCreateStore(userId, { name: storeName });
      if (newStore) setStores(prev => [...prev, newStore]);
    } else if (mode == "edit" && store){
      const updatedStore = await handleUpdateStore(store?.id, {name: storeName});
      setStores(prev => prev.map(store => store.id == updatedStore?.id ? updatedStore : store));
    }

    onCancel();
  };

  return (
    <div className="w-full max-w-md flex flex-col gap-3 mt-4">
      <form onSubmit={handleSubmit} className="flex flex-col gap-3">
        {error && <ErrorMessage message={error} />}
        <input
          type="text"
          placeholder="Enter store name"
          value={storeName}
          onChange={(e) => setStoreName(e.target.value)}
          className="p-3 rounded bg-background border border-btn-primary text-white"
          required
        />

        <div className="flex gap-4 justify-end">
          <button
            type="button"
            onClick={onCancel}
            className="bg-primary hover:bg-primary-hover text-white font-bold py-3 px-6 rounded transition"
          >
            Cancel
          </button>

          <button
            type="submit"
            className="bg-primary hover:bg-primary-hover text-white font-bold py-3 px-6 rounded transition"
          >
            {buttonLabel}
          </button>
        </div>

      </form>
    </div>
  );
};

export default StoreForm;
