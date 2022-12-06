package com.ingsoftware.contactmanager.exception;

import com.ingsoftware.contactmanager.entity.Contact;

public class ContactException extends RuntimeException{
    public ContactException(String message) {
        super(message);
    }
}
