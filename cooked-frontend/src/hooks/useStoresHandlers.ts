import { useState } from "react";
import {
  createStore,
  getStoresByUser,
  getStoreById,
  updateStore,
  deleteStore,
  StoreRequest,
  StoreResponse,
} from "../api/store";

export function useCreateStoreHandler() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleCreateStore = async (userId: string, data: StoreRequest): Promise<StoreResponse | null> => {
    setLoading(true);
    setError(null);

    try {
      const store = await createStore(userId, data);
      return store ?? null;
    } catch (err) {
      const message = (err as Error).message || "Failed to create store.";
      setError(message);
      return null
    } finally {
      setLoading(false);
    }
  };

  return { handleCreateStore, loading, error, setError };
}

export function useGetStoresByUserHandler() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [stores, setStores] = useState<StoreResponse[]>([]);

  const handleGetStoresByUser = async (userId: string) => {
    setLoading(true);
    setError(null);

    try {
      const data = await getStoresByUser(userId);
      if (data) setStores(data);
    } catch (err) {
      const message = (err as Error).message || "Failed to fetch stores.";
      setError(message);
    } finally {
      setLoading(false);
    }
  };

  return { stores, handleGetStoresByUser, loading, error, setError , setStores};
}

export function useGetStoreByIdHandler() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [store, setStore] = useState<StoreResponse | null>(null);

  const handleGetStoreById = async (storeId: string) => {
    setLoading(true);
    setError(null);

    try {
      const data = await getStoreById(storeId);
      if (data) setStore(data);
    } catch (err) {
      const message = (err as Error).message || "Failed to fetch store.";
      setError(message);
    } finally {
      setLoading(false);
    }
  };

  return { store, handleGetStoreById, loading, error, setError };
}

export function useUpdateStoreHandler() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [store, setStore] = useState<StoreResponse | null>(null);


  const handleUpdateStore = async (storeId: string, data: StoreRequest): Promise<StoreResponse | null> => {
    setLoading(true);
    setError(null);

    try {
      const updatedStore = await updateStore(storeId, data);
      if (updatedStore) setStore(updatedStore);
      return updatedStore ?? null
    } catch (err) {
      const message = (err as Error).message || "Failed to update store.";
      setError(message);
      return null;
    } finally {
      setLoading(false);
    }
  };

  return { handleUpdateStore, loading, error, setError, store };
}

export function useDeleteStoreHandler() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleDeleteStore = async (storeId: string) => {
    setLoading(true);
    setError(null);

    try {
      const deleted = await deleteStore(storeId);
      return deleted;
    } catch (err) {
      const message = (err as Error).message || "Failed to delete store.";
      setError(message);
    } finally {
      setLoading(false);
    }
  };

  return { handleDeleteStore, loading, error, setError };
}
