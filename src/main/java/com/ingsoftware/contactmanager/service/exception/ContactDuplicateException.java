package com.ingsoftware.contactmanager.service.exception;

public class ContactDuplicateException extends RuntimeException{
    public ContactDuplicateException(String message) {
        super(message);
    }
}
