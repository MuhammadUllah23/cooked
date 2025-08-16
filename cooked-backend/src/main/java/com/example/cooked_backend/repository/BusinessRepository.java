package com.example.cooked_backend.repository;

import com.example.cooked_backend.model.Business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, UUID> {
    Optional<List<Business>> findAllByUserId(UUID userid);
}
