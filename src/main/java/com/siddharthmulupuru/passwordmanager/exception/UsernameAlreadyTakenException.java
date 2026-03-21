package com.siddharthmulupuru.passwordmanager.exception;

public class UsernameAlreadyTakenException extends RuntimeException {
    public UsernameAlreadyTakenException() {
        super("Username already taken");
    }
}
