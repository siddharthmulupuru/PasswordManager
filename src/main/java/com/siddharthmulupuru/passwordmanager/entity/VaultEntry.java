package com.siddharthmulupuru.passwordmanager.entity;

import java.util.UUID;
import java.time.LocalDateTime;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "vault_entries")
public class VaultEntry {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    private UUID id;
    
    private String encryptedName;
    private String encryptedDescription;
    private String encryptedWebsiteUsername;
    private String encryptedWebsitePassword;
    private String encryptedWebsite;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public VaultEntry() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public VaultEntry(User user, String encryptedName, String encryptedDescription,
        String encryptedWebsiteUsername, String encryptedWebsitePassword, String encryptedWebsite) {
            this();
            this.user = user;
            this.encryptedName = encryptedName;
            this.encryptedDescription = encryptedDescription;
            this.encryptedWebsiteUsername = encryptedWebsiteUsername;
            this.encryptedWebsitePassword = encryptedWebsitePassword;
            this.encryptedWebsite = encryptedWebsite;
    }

    // Getter methods
    public User getUser() { return user; }
    public String getEncryptedName() { return encryptedName; }
    public String getEncryptedDescription() { return encryptedDescription; }
    public String getEncryptedWebsiteUsername() { return encryptedWebsiteUsername; }
    public String getEncryptedWebsitePassword() { return encryptedWebsitePassword; }
    public String getEncryptedWebsite() { return encryptedWebsite; }
    public UUID getId() { return id; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setter methods
    private void setUpdatedAt() { this.updatedAt = LocalDateTime.now(); }

    public void setEncryptedName(String encryptedName) {
        this.encryptedName = encryptedName;
        setUpdatedAt();
    }

    public void setEncryptedWebsitePassword(String encryptedPassword) {
        this.encryptedWebsitePassword = encryptedPassword;
        setUpdatedAt();
    }

    public void setEncryptedDescription(String encryptedDescription) {
        this.encryptedDescription = encryptedDescription;
        setUpdatedAt();
    }

    public void setEncryptedWebsiteUsername(String encryptedWebsiteUsername) {
        this.encryptedWebsiteUsername = encryptedWebsiteUsername;
        setUpdatedAt();
    }

    public void setEncryptedWebsite(String encryptedWebsite) {
        this.encryptedWebsite = encryptedWebsite;
        setUpdatedAt();
    }
}
