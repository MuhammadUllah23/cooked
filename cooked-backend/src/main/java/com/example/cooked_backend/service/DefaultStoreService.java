package com.example.cooked_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cooked_backend.dto.request.StoreRequest;
import com.example.cooked_backend.dto.response.StoreResponse;
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
	public StoreResponse getStoreById(UUID storeId, UUID userId) {
		Store store = storeRepository.findByIdAndUserId(storeId, userId)
			.orElseThrow(() -> ServiceException.of(ErrorCode.STORE_NOT_FOUND)
											.addDetail("storeId", storeId)
											.addDetail("userId", userId));
    	return new StoreResponse(store);
	}

	@Override
	@Transactional
	public StoreResponse createStore(StoreRequest storeRequest, UUID userId) {
		try {
			checkStoreAlreadyExistsByName(storeRequest.getName(), userId);

			long currentStoreCount = storeRepository.countByUserId(userId);
			if (currentStoreCount >= STORE_LIMIT_PER_USER) {
				throw ServiceException.of(ErrorCode.STORE_LIMIT_REACHED)
				.addDetail("userId", userId);
			}

			Store store = new Store(storeRequest, userId);

			Store createdstore =  storeRepository.save(store);
			StoreResponse storeResponse = new StoreResponse(createdstore);

			return storeResponse;

		} catch (DataIntegrityViolationException ex) {
			throw ServiceException.of(ErrorCode.STORE_ALREADY_EXISTS)
				.addDetail("userId", userId)
				.addDetail("storeName", storeRequest.getName());
		}
	}

	@Override
	public void deleteStoreById(UUID storeId, UUID userId) {
		Store store = storeRepository.findByIdAndUserId(storeId, userId)
									.orElseThrow(() -> ServiceException.of(ErrorCode.STORE_NOT_FOUND)
									.addDetail("storeId", storeId)
									.addDetail("userId", userId));
									
		storeRepository.delete(store);
	}

	@Override
	public List<StoreResponse> getAllStoresByUserId(UUID userId) {
		List<Store> stores = storeRepository.findAllByUserId(userId);
		List<StoreResponse> storeListResponse = stores.stream().map(StoreResponse::new).toList();

		return storeListResponse;
	}


	private void checkStoreAlreadyExistsByName(String storeName, UUID userId) {
        boolean nameExists = storeRepository.existsByUserIdAndNameIgnoreCase(userId, storeName);

        if (nameExists == true) {
            throw ServiceException.of(ErrorCode.USER_ALREADY_EXISTS)
                        .addDetail("name", storeName)
						.addDetail("userId", userId);
        }
    }
}
