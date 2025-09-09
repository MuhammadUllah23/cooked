package com.example.cooked_backend.dto.response;

import java.util.UUID;

import com.example.cooked_backend.model.User;
import com.example.cooked_backend.model.UserRole;

public class UserResponse {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role;

    public UserResponse (User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole getRole() { 
        return role; 
    }

    public void setRole(UserRole role) { 
        this.role = role; 
    }
}
