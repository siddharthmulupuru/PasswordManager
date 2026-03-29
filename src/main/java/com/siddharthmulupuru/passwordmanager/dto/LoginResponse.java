package com.siddharthmulupuru.passwordmanager.dto;

public class LoginResponse {
    private String token;
    private String salt;

    public LoginResponse(String token, String salt) {
        this.token = token;
        this.salt = salt;
    }

    // Getter methods
    public String getToken() { return token; }
    public String getSalt() { return salt; }
}
