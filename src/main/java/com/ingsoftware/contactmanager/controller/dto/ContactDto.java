package com.ingsoftware.contactmanager.controller.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

public class ContactDto {
    @Size(min = 3, max = 40, message = "First name size must be between 3 and 40")
    @NotBlank(message = "First name shouldn't be empty.")
    private String firstName;
    @Size(min = 3, max = 40, message = "Last name size must be between 3 and 40")
    @NotBlank(message = "Last name shouldn't be empty.")
    private String lastName;
    @Size(min = 3, max = 50, message = "Address size must be between 3 and 50")
    @NotBlank(message = "Address shouldn't be empty.")
    private String address;
    @Size(min = 3, max = 50, message = "Phone number size must be between 3 and 50")
    @NotBlank(message = "Phone number shouldn't be empty.")
    private String phoneNumber;
    @NotBlank(message = "Email shouldn't be empty.")
    @Email(message = "email should be a valid email")
    private String email;
    private UUID contactTypeId;

    public UUID getContactTypeId() {
        return contactTypeId;
    }

    public void setContactTypeId(UUID contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

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

