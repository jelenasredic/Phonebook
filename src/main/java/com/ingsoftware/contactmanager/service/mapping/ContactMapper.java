package com.ingsoftware.contactmanager.service.mapping;

import com.ingsoftware.contactmanager.dto.ContactDto;
import com.ingsoftware.contactmanager.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {
    public Contact mapToEntity (ContactDto contactDto){
        Contact contact = new Contact();
        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setAddress(contactDto.getAddress());
        contact.setPhoneNumber(contactDto.getPhoneNumber());
        contact.setEmail(contactDto.getEmail());

        return contact;
    }
}
