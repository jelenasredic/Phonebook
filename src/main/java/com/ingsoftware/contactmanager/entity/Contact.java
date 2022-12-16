package com.ingsoftware.contactmanager.entity;


import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Size(min = 3, max = 40, message = "First name size must be between 3 and 40")
    private String firstName;

    @Size(min = 3, max = 40, message = "Last name size must be between 3 and 40")
    private String lastName;

    @Size(min = 3, max = 50, message = "Address size must be between 3 and 50")
    private String address;

    @Size(min = 3, max = 50, message = "Phone number size must be between 3 and 50")
    private String phoneNumber;

    @NonNull
    @Email(message = "email should be a valid email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    private ContactType contactType;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }
}
