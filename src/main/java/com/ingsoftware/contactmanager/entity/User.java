package com.ingsoftware.contactmanager.entity;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private UUID id;

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    @Size(min = 3, max = 40, message = "First name size must be between 3 and 40")
    private String firstName;
    @Size(min = 3, max = 40, message = "Last name size must be between 3 and 40")
    private String lastName;
    @Column(unique = true)
    @Email(message = "Email should be a valid email")
    private String email;
    @NonNull
    @Size(min = 3, max = 100, message = "Password size must be between 3 and 100")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @NonNull
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Contact> contactList = new ArrayList<>();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public Role getRole() {
        return role;
    }

    public void setRole(@NonNull Role role) {
        this.role = role;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

}

