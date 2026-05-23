package com.siddharthmulupuru.passwordmanager.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vault_entries")
public class VaultEntry {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String encryptedName;

    @Column(columnDefinition = "TEXT")
    private String encryptedDescription;

    @Column(columnDefinition = "TEXT")
    private String encryptedWebsiteUsername;

    @Column(columnDefinition = "TEXT")
    private String encryptedWebsiteEmail;

    @Column(columnDefinition = "TEXT")
    private String encryptedWebsitePassword;

    @Column(columnDefinition = "TEXT")
    private String encryptedWebsite;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public VaultEntry() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public VaultEntry(User user, String encryptedName, String encryptedDescription,
        String encryptedWebsiteUsername, String encryptedWebsiteEmail,
        String encryptedWebsitePassword, String encryptedWebsite) {
            this();
            this.user = user;
            this.encryptedName = encryptedName;
            this.encryptedDescription = encryptedDescription;
            this.encryptedWebsiteUsername = encryptedWebsiteUsername;
            this.encryptedWebsiteEmail = encryptedWebsiteEmail;
            this.encryptedWebsitePassword = encryptedWebsitePassword;
            this.encryptedWebsite = encryptedWebsite;
    }

    // Getter methods
    public User getUser() { return user; }
    public String getEncryptedName() { return encryptedName; }
    public String getEncryptedDescription() { return encryptedDescription; }
    public String getEncryptedWebsiteUsername() { return encryptedWebsiteUsername; }
    public String getEncryptedWebsiteEmail() { return encryptedWebsiteEmail; }
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

    public void setEncryptedWebsiteEmail(String encryptedWebsiteEmail) {
        this.encryptedWebsiteEmail = encryptedWebsiteEmail;
        setUpdatedAt();
    }

    public void setEncryptedWebsite(String encryptedWebsite) {
        this.encryptedWebsite = encryptedWebsite;
        setUpdatedAt();
    }
}
