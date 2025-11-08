package com.example.cooked_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.cooked_backend.dto.request.StoreRequest;
import com.example.cooked_backend.model.Store;

public interface StoreService {
    List<Store> getAllStoresByUserId(UUID userId);
    Optional<Store> getStoreById(UUID id);
    Store createStore(StoreRequest storeRequest);
    void deleteStoreById(UUID id);
}
