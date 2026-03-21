package com.siddharthmulupuru.passwordmanager.exception;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException() {
        super("Invalid username or password");
    }
}
