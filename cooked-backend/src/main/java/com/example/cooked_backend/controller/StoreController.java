package com.example.cooked_backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import com.example.cooked_backend.service.DefaultStoreService;
import com.example.cooked_backend.dto.request.StoreRequest;
import com.example.cooked_backend.dto.response.StoreResponse;
import com.example.cooked_backend.model.CustomUserDetails;

@RestController
@RequestMapping("/api/store")
public class StoreController {
	
    private final DefaultStoreService defaultStoreService;

    public StoreController(DefaultStoreService defaultStoreService) {
        this.defaultStoreService = defaultStoreService;
    }

    @PostMapping()
    public ResponseEntity<StoreResponse> createStore(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                    @Valid @RequestBody StoreRequest storeRequest,
                                                    HttpServletResponse response) {
        StoreResponse storeResponse = defaultStoreService.createStore(storeRequest, customUserDetails.getId());
        return ResponseEntity.ok(storeResponse);
    }

    @PutMapping("/{storeId}")
    public ResponseEntity<StoreResponse> updateStore(
        @AuthenticationPrincipal CustomUserDetails customUserDetails,
        @PathVariable UUID storeId,
        @Valid @RequestBody StoreRequest storeRequest) {

        StoreResponse updatedStore = defaultStoreService.updateStore(storeRequest,storeId, customUserDetails.getId());
        return ResponseEntity.ok(updatedStore);
    }


    @GetMapping()
    public ResponseEntity<List<StoreResponse>> getAllStoresByUser(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<StoreResponse> storeListResponse = defaultStoreService.getAllStoresByUserId(customUserDetails.getId());                                                
        return ResponseEntity.ok(storeListResponse);
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponse> getStoreById(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                        @PathVariable UUID storeId) {
        StoreResponse storeResponse = defaultStoreService.getStore(storeId, customUserDetails.getId());
        return ResponseEntity.ok(storeResponse);
    }

    
    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteStore(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                            @PathVariable UUID storeId) {

        defaultStoreService.deleteStoreById(storeId, customUserDetails.getId());
        return ResponseEntity.noContent().build();
    }

}
