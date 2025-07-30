package com.example.cooked_backend.service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
import com.example.cooked_backend.model.User;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(UUID id);
    User createUser(User user);
    void deleteUserById(UUID id);
}
