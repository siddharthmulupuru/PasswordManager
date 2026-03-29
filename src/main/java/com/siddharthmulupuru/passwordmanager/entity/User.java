package com.siddharthmulupuru.passwordmanager.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;

    @Column(unique = true)
    private String username;
    
    private LocalDateTime createdAt;
    private String passwordHash;
    private String salt;

    public User() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    public User(String username, String passwordHash, String salt) {
        this();
        this.username = username;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    // Getter methods
    public UUID getId() { return id; }
    public String getSalt() { return salt; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    // Setter methods
    public void setUsername(String username) { this.username = username; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}
