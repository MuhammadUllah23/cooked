package com.example.cooked_backend.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cooked_backend.repository.UserRepository;

import com.example.cooked_backend.dto.request.UserRequest;
import com.example.cooked_backend.dto.response.UserResponse;
import com.example.cooked_backend.exception.ErrorCode;
import com.example.cooked_backend.exception.ServiceException;
import com.example.cooked_backend.model.User;

@Service
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    // Constructor Injection
    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public UserResponse getUserByEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));
            
        return new UserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        checkUserAlreadyExistsByEmail(userRequest.getEmail());
        User user = new User(userRequest);
        
        User createdUser = userRepository.save(user);

        UserResponse userResponse = new UserResponse(createdUser);
        System.out.println("after user creation " + userResponse.toString());

        return userResponse;
    }

    @Override
    @Transactional
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    private void checkUserAlreadyExistsByEmail(String email) {
        boolean userExists = userRepository.existsByEmail(email);

        if (userExists == true) {
            throw ServiceException.of(ErrorCode.USER_ALREADY_EXISTS)
                        .addDetail("email", email);
        }
    }
}