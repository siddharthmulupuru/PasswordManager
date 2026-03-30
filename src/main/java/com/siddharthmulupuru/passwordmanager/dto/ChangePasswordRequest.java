package com.siddharthmulupuru.passwordmanager.dto;

import java.util.List;

public class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
    private String newSalt;
    private List<VaultEntryRequest> entries;

    // Getter methods
    public String getCurrentPassword() { return currentPassword; }
    public String getNewPassword() { return newPassword; }
    public String getNewSalt() { return newSalt; }
    public List<VaultEntryRequest> getEntries() { return entries; }

    // Setter methods
    public void setCurrentPassword(String currentPassword) { this.currentPassword = currentPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
    public void setNewSalt(String newSalt) { this.newSalt = newSalt; }
    public void setEntries(List<VaultEntryRequest> entries) { this.entries = entries; }
}
