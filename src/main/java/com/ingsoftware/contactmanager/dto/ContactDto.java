package com.ingsoftware.contactmanager.dto;

import javax.validation.constraints.NotBlank;

public class ContactDto {
    @NotBlank(message = "First name shouldn't be empty.")
    private String firstName;
    @NotBlank(message = "Last name shouldn't be empty.")
    private String lastName;
    @NotBlank(message = "Address shouldn't be empty.")
    private String address;
    @NotBlank(message = "Phone number shouldn't be empty.")
    private String phoneNumber;

    @NotBlank(message = "Empty houldn't be empty.")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

