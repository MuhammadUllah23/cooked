package com.example.cooked_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.cooked_backend.dto.request.StoreRequest;
import com.example.cooked_backend.exception.ErrorCode;
import com.example.cooked_backend.exception.ServiceException;
import com.example.cooked_backend.model.Store;
import com.example.cooked_backend.repository.StoreRepository;


@Service
public class DefaultStoreService implements StoreService {

	private final StoreRepository storeRepository;

	private static final int STORE_LIMIT_PER_USER = 3;

	// Constructor Injection
	public DefaultStoreService(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}

	@Override
	public Optional<Store> getStoreById(UUID storeId) {
		Optional<Store> store = storeRepository.findById(storeId);
		return store;
	}

	@Override
	public Store createStore(StoreRequest storeRequest) {
		Store store = new Store(storeRequest);

		Store createdstore =  storeRepository.save(store);
		return createdstore;
	}

	@Override
	public void deleteStoreById(UUID storeId) {
		storeRepository.deleteById(storeId);
	}

	@Override
	public List<Store> getAllStoresByUserId(UUID userId) {
		return storeRepository.findAllByUserId(userId).orElse(List.of());
	}
}
