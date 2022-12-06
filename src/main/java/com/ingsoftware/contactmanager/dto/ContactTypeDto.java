package com.ingsoftware.contactmanager.dto;

import javax.validation.constraints.NotBlank;

public class ContactTypeDto {
    @NotBlank (message = "Type sholudn't be empty")
    private String type;
    @NotBlank (message = "Description shouldn't be empty")
    private String description;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
