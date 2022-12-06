package com.ingsoftware.contactmanager.entity;


import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "contact_type")
public class ContactType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ID;
    private String description;
   @NonNull
   private String type;


    public ContactType() {
    }

    public ContactType(String description, String type) {
        this.description = description;
        this.type = type;
    }

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }
}

