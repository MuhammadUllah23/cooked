package com.example.cooked_backend.repository;

import com.example.cooked_backend.model.Store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID> {
    Optional<List<Store>> findAllByUserId(UUID userid);
    boolean existsByUserIdAndNameIgnoreCase(UUID userId, String name);
    long countByUserId(UUID userId);
}
