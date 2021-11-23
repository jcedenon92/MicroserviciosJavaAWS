package com.aforo255.appsecurity.dtos;

import java.io.Serializable;
 
public class AuthResponse implements Serializable {
    private final String token;
    private final String userName;
    private final String accessTokenExpiration;
 
    public AuthResponse(String token, String userName, String accessTokenExpiration) {
        this.token = token;
        this.userName = userName;
        this.accessTokenExpiration = accessTokenExpiration;
    }
 
    public String getToken() {
        return token;
    }
    public String getAccessTokenExpiration() {
        return accessTokenExpiration;
    }
    public String getUserName() {
        return userName;
    }
}
