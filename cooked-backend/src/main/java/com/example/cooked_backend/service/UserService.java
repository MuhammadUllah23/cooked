package com.example.cooked_backend.service;

import java.util.Optional;
import java.util.UUID;

import com.example.cooked_backend.dto.request.UserRequest;
import com.example.cooked_backend.dto.response.UserResponse;
import com.example.cooked_backend.model.User;

public interface UserService {
    Optional<User> getUserById(UUID id);
    UserResponse createUser(UserRequest user);
    void deleteUserById(UUID id);
}
