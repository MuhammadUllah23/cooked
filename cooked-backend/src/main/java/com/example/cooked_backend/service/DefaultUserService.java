package com.example.cooked_backend.service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cooked_backend.repository.UserRepository;
import com.example.cooked_backend.model.User;

@Service
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    // Constructor Injection
    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        try {
            Optional<User> user = userRepository.findById(id);
            return  user;
        }
        catch (Exception e) {
            throw new RuntimeException("User not found with given user id: " + id, e);
        }
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }
}
