package com.siddharthmulupuru.passwordmanager.dto;

import java.util.List;

public class ChangePasswordResponse {
    private String token;
    private String salt;
    private List<VaultEntryResponse> entries;

    public ChangePasswordResponse(String token, String salt, List<VaultEntryResponse> entries) {
        this.token = token;
        this.salt = salt;
        this.entries = entries;
    }

    // Getter methods
    public String getToken() { return token; }
    public String getSalt() { return salt; }
    public List<VaultEntryResponse> getEntries() { return entries; }
}
