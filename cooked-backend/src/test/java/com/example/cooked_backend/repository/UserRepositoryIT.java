package com.example.cooked_backend.repository;

import com.example.cooked_backend.containers.AbstractPostgresContainerTest;

import com.example.cooked_backend.model.User;
import com.example.cooked_backend.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryIT extends AbstractPostgresContainerTest {

    @Autowired
    private UserRepository userRepository; 

    @Test
    public void testCreateUser() {
        User user = new User();

        user.setEmail("test@example.com");
        user.setPassword("securePassword123");
        user.setFirstName("Test User");
        user.setLastName("Doe");
        
        User savedUser = userRepository.save(user);

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo("test@example.com");
        assertThat(savedUser.getFirstName()).isEqualTo("Test User");
    }

    @Test
    public void testUpdateUser() {
        // First create and save a user
        User user = new User();

        user.setEmail("update@example.com");
        user.setPassword("securePassword123");
        user.setFirstName("Initial Name");
        user.setLastName("Doe");
        User savedUser = userRepository.save(user);

        // Update the user name
        savedUser.setFirstName("Updated Name");
        User updatedUser = userRepository.save(savedUser);

        assertThat(updatedUser.getFirstName()).isEqualTo("Updated Name");
    }
    
    @Test
    public void testFindUserById() {
        User user = new User();
        user.setEmail("find@example.com");
        user.setPassword("securePassword123");
        user.setFirstName("Find User");
        user.setLastName("Doe");
        User savedUser = userRepository.save(user);

        Optional<User> foundUser = userRepository.findById(savedUser.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("find@example.com");
    }

    @Test
    public void testSaveAndFindByEmail() {
        User user = new User();
        user.setEmail("test2@example.com");
        user.setPassword("securePassword123");
        user.setFirstName("John");
        user.setLastName("Doe");

        userRepository.save(user);

        Optional<User> found = userRepository.findByEmail("test2@example.com");
        assertThat(found).isPresent();
        assertThat(found.get().getFirstName()).isEqualTo("John");
    }

    @Test
    public void testExistsByEmail() {
        User user = new User();
        user.setEmail("exists@example.com");
        user.setPassword("password123");
        user.setFirstName("Jane");
        user.setLastName("Smith");

        userRepository.save(user);

        boolean exists = userRepository.existsByEmail("exists@example.com");
        assertThat(exists).isTrue();

        boolean notExists = userRepository.existsByEmail("nope@example.com");
        assertThat(notExists).isFalse();
    }
}
