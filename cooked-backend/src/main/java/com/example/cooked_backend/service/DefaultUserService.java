package com.example.cooked_backend.service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cooked_backend.repository.UserRepository;

import jakarta.persistence.EntityExistsException;

import com.example.cooked_backend.dto.request.UserRequest;
import com.example.cooked_backend.dto.response.UserResponse;
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
    
        Optional<User> user = userRepository.findById(id);
        return user;

    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        checkUserAlreadyExistsByEmail(userRequest.getEmail());
        User user = new User(userRequest);

        User createdUser = userRepository.save(user);

        UserResponse userResponse = new UserResponse(user);

        return userResponse;
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    private void checkUserAlreadyExistsByEmail(String email) {
        boolean userExists = userRepository.existsByEmail(email);

        if (userExists == true) {
            throw new EntityExistsException("An account already exists with the email " + email);
        }
    }
}
