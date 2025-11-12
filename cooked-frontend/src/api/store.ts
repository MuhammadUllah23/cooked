import api from "./api";
import { AxiosResponse } from "axios";
import { handleApiError } from "./util/errorHandler";

export interface StoreRequest {
  name: string;
}

export interface StoreResponse {
  id: string;
  name: string;
  userId: string;
}

export const createStore = async (userId: string, storeRequest: StoreRequest): Promise<StoreResponse | null> => {
  try {
    const response: AxiosResponse<StoreResponse> = await api.post(`/stores/${userId}`, storeRequest);
    return response.data;
  } catch (error) {
    handleApiError(error);
    return null;
  }
};

// get stores by user
export const getStoresByUser = async (userId: string): Promise<StoreResponse[] | null> => {
  try {
    const response: AxiosResponse<StoreResponse[]> = await api.get(`/stores/user/${userId}`);
    return response.data;
  } catch (error) {
    handleApiError(error);
    return null;
  }
};

// get store by store ID
export const getStoreById = async (id: string): Promise<StoreResponse | null> => {
  try {
    const response: AxiosResponse<StoreResponse> = await api.get(`/stores/${id}`);
    return response.data;
  } catch (error) {
    handleApiError(error);
    return null;
  }
};


export const updateStore = async (id: string, storeRequest: StoreRequest): Promise<StoreResponse | null> => {
  try {
    const response: AxiosResponse<StoreResponse> = await api.put(`/stores/${id}`, storeRequest);
    return response.data;
  } catch (error) {
    handleApiError(error);
    return null;
  }
};


export const deleteStore = async (id: string): Promise<boolean> => {
  try {
    await api.delete(`/stores/${id}`);
    return true;
  } catch (error) {
    handleApiError(error);
    return false;
  }
};