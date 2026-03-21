package com.siddharthmulupuru.passwordmanager.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.siddharthmulupuru.passwordmanager.entity.VaultEntry;

public class VaultEntryResponse {
    private UUID id;
    private String encryptedName;
    private String encryptedDescription;
    private String encryptedWebsiteUsername;
    private String encryptedWebsitePassword;
    private String encryptedWebsite;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public VaultEntryResponse(VaultEntry entry) {
        this.id = entry.getId();
        this.encryptedName = entry.getEncryptedName();
        this.encryptedDescription = entry.getEncryptedDescription();
        this.encryptedWebsiteUsername = entry.getEncryptedWebsiteUsername();
        this.encryptedWebsitePassword = entry.getEncryptedWebsitePassword();
        this.encryptedWebsite = entry.getEncryptedWebsite();
        this.createdAt = entry.getCreatedAt();
        this.updatedAt = entry.getUpdatedAt();
    }

    // Getter methods
    public UUID getId() { return id; }
    public String getEncryptedName() { return encryptedName; }
    public String getEncryptedDescription() { return encryptedDescription; }
    public String getEncryptedWebsiteUsername() { return encryptedWebsiteUsername; }
    public String getEncryptedWebsitePassword() { return encryptedWebsitePassword; }
    public String getEncryptedWebsite() { return encryptedWebsite; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
