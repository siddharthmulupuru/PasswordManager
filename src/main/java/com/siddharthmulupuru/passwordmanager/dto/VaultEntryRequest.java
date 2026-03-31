package com.siddharthmulupuru.passwordmanager.dto;

public class VaultEntryRequest {
    private String encryptedName;
    private String encryptedDescription;
    private String encryptedWebsiteUsername;
    private String encryptedWebsiteEmail;
    private String encryptedWebsitePassword;
    private String encryptedWebsite;

    // Getter methods
    public String getEncryptedName() { return encryptedName; }
    public String getEncryptedDescription() { return encryptedDescription; }
    public String getEncryptedWebsiteUsername() { return encryptedWebsiteUsername; }
    public String getEncryptedWebsiteEmail() { return encryptedWebsiteEmail; }
    public String getEncryptedWebsitePassword() { return encryptedWebsitePassword; }
    public String getEncryptedWebsite() { return encryptedWebsite; }

    // Setter methods
    public void setEncryptedName(String encryptedName) { this.encryptedName = encryptedName; }
    public void setEncryptedDescription(String encryptedDescription) { this.encryptedDescription = encryptedDescription; }
    public void setEncryptedWebsiteUsername(String encryptedWebsiteUsername) { this.encryptedWebsiteUsername = encryptedWebsiteUsername; }
    public void setEncryptedWebsiteEmail(String encryptedWebsiteEmail) { this.encryptedWebsiteEmail = encryptedWebsiteEmail; }
    public void setEncryptedWebsitePassword(String encryptedWebsitePassword) { this.encryptedWebsitePassword = encryptedWebsitePassword; }
    public void setEncryptedWebsite(String encryptedWebsite) { this.encryptedWebsite = encryptedWebsite; }
}
