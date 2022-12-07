package com.ingsoftware.contactmanager.service.exception;

public class ContactTypeDuplicateException extends RuntimeException{

    public ContactTypeDuplicateException(String message){
    super(message);
    }
}
