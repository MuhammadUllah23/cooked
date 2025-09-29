package com.example.cooked_backend.dto.response;


public class RefreshResopnse {
    private String accessToken;
    private String refreshToken;
    
    public RefreshResopnse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAcessToken() {
        return accessToken;
    }

    public void setAccesToken(String accessToken) {
        this.accessToken = accessToken;
    }    
    
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
