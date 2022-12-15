package com.ingsoftware.contactmanager.service.mapping;

import com.ingsoftware.contactmanager.controller.dto.ContactDto;
import com.ingsoftware.contactmanager.entity.Contact;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContactMapper {
    public Contact mapToEntity(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setAddress(contactDto.getAddress());
        contact.setPhoneNumber(contactDto.getPhoneNumber());
        contact.setEmail(contactDto.getEmail());

        return contact;
    }

    public ContactDto convertContactToDto(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setFirstName(contact.getFirstName());
        contactDto.setLastName(contact.getLastName());
        contactDto.setAddress(contact.getAddress());
        contactDto.setPhoneNumber(contact.getPhoneNumber());
        contactDto.setEmail(contact.getEmail());
        contactDto.setContactTypeId(contact.getContactType().getId());
        return contactDto;
    }

    public List<ContactDto> contactDtoList(List<Contact> contactList) {
        List<ContactDto> contactDtoList = new ArrayList<>();
        for (var contact : contactList) {
            var contactDto = convertContactToDto(contact);
            contactDtoList.add(contactDto);
        }
        return contactDtoList;
    }
}
