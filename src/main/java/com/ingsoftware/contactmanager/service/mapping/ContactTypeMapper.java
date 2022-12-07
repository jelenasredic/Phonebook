package com.ingsoftware.contactmanager.service.mapping;

import com.ingsoftware.contactmanager.controller.dto.ContactTypeDto;
import com.ingsoftware.contactmanager.entity.ContactType;
import org.springframework.stereotype.Component;

@Component
public class ContactTypeMapper {
    public ContactType mapToEntity (ContactTypeDto contactTypeDto){
        ContactType contactType = new ContactType();
        contactType.setType(contactTypeDto.getType());
        contactType.setDescription(contactTypeDto.getDescription());
        return contactType;
    }
}
