package com.ingsoftware.contactmanager.controller.dto;

import com.ingsoftware.contactmanager.entity.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {

    @Size(min = 3, max = 40, message = "First name size must be between 3 and 40")
    @NotBlank(message = "First name shouldn't be blank.")
    private String firstName;

    @Size(min = 3, max = 40, message = "Last name size must be between 3 and 40")
    @NotBlank(message = "Last name shouldn't be blank.")
    private String lastName;
    @Size(min = 3, max = 100, message = "Password size must be between 3 and 100")
    @NotBlank(message = "Password shouldn't be blank.")
    private String password;

    //@Email(message = "Email should be a valid email")
    @NotBlank (message = "Email shouldn't be blank")
    private String email;
    @Size(min = 3, max = 40, message = "Password size must be between 3 and 40")
    @NotBlank(message = "Role shouldn't be blank.")
    private Role role;

    public UserDto(String firstName, String lastName, String password, String email, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
