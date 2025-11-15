import { useState } from "react";

interface StoreFormProps {
  userId?: string;
  initialName?: string;
  mode?: "create" | "edit";
  onCancel: () => void;
  onSubmit: (storeName: string) => void;
}

const StoreForm: React.FC<StoreFormProps> = ({
  userId,
  initialName = "",
  mode = "create",
  onCancel,
  onSubmit,
}) => {

  const [storeName, setStoreName] = useState(initialName);
  const buttonLabel = mode === "edit" ? "Save" : "Create";

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (!storeName.trim()) return;

    onSubmit(storeName);
  };

  return (
    <div className="w-full max-w-md flex flex-col gap-3 mt-4">
      <form onSubmit={handleSubmit} className="flex flex-col gap-3">

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
