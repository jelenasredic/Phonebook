package com.ingsoftware.contactmanager.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ContactTypeDto {

    @Size(min = 3, max = 40, message = "Type size must be between 3 and 40")
    @NotBlank(message = "Type sholudn't be empty")
    private String type;
    @Size(min = 3, max = 100, message = "Description size must be between 3 and 100")
    @NotBlank(message = "Description shouldn't be empty")
    private String description;

    public ContactTypeDto() {
    }

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
