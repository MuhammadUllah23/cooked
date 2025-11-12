package com.example.cooked_backend.dto.response;

import java.util.UUID;

import com.example.cooked_backend.model.Store;

public class StoreResponse {
    private UUID id;
    private String name;
    private UUID userId;


    public StoreResponse (Store store) {
        this.id = store.getId();
        this.name = store.getName();
        this.userId = store.getUserId();
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
