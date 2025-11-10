package com.example.cooked_backend.service;

import java.util.List;
import java.util.UUID;

import com.example.cooked_backend.dto.request.StoreRequest;
import com.example.cooked_backend.dto.response.StoreResponse;

public interface StoreService {
    List<StoreResponse> getAllStoresByUserId(UUID userId);
    StoreResponse getStoreById(UUID id, UUID userId);
    StoreResponse createStore(StoreRequest storeRequest, UUID userId);
    void deleteStoreById(UUID id);
}
