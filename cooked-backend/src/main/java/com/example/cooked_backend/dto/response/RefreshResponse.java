package com.example.cooked_backend.dto.response;


public class RefreshResponse {
    private String accessToken;
    
    public RefreshResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
    }

    public String getAcessToken() {
        return accessToken;
    }

    public void setAccesToken(String accessToken) {
        this.accessToken = accessToken;
    }    
    
}
