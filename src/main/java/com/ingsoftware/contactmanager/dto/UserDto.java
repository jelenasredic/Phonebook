package com.ingsoftware.contactmanager.dto;

import com.ingsoftware.contactmanager.entity.Role;

import javax.validation.constraints.NotBlank;

public class UserDto {

    @NotBlank(message = "Password shouldn't be blank.")
    private String password;

    @NotBlank (message = "Email shouldn't be blank")
    private String email;
    private Role role;

    public UserDto(String password, String email, Role role) {
        this.password = password;
        this.email = email;
        this.role = role;
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
