package com.siddharthmulupuru.passwordmanager.exception;

public class VaultEntryNotFoundException extends RuntimeException {
    public VaultEntryNotFoundException() {
        super("Vaulty entry not found");
    }
}
