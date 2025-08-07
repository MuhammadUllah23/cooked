package com.example.cooked_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.cooked_backend.model.Business;

public interface BusinessService {
    List<Business> getAllBusinessesByUserId(UUID userId);
    Optional<Business> getBusinessById(UUID id);
    Business createUser(Business business);
    void deleteBusinessById(UUID id);
}
