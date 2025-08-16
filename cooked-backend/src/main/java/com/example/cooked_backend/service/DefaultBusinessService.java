package com.example.cooked_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.cooked_backend.model.Business;
import com.example.cooked_backend.repository.BusinessRepository;


@Service
public class DefaultBusinessService implements BusinessService {

	private final BusinessRepository businessRepository;

	// Constructor Injection
	public DefaultBusinessService(BusinessRepository businessRepository) {
		this.businessRepository = businessRepository;
	}

	@Override
	public Optional<Business> getBusinessById(UUID businessId) {
		Optional<Business> business = businessRepository.findById(businessId);
		return business;
	}

	@Override
	public Business createBusiness(Business business) {
		// TODO: implement this method
		return null;
	}

	@Override
	public void deleteBusinessById(UUID businessId) {
		// TODO: implement this method
	}

	@Override
	public List<Business> getAllBusinessesByUserId(UUID userId) {
		return businessRepository.findAllByUserId(userId).orElse(List.of());
	}

}
