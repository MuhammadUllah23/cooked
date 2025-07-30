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
        return userRepository.findById(id);
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
