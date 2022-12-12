package com.ingsoftware.contactmanager.service;


import com.ingsoftware.contactmanager.controller.dto.ContactDto;
import com.ingsoftware.contactmanager.controller.dto.ContactTypeDto;
import com.ingsoftware.contactmanager.entity.Contact;
import com.ingsoftware.contactmanager.entity.ContactType;
import com.ingsoftware.contactmanager.repository.ContactRepository;
import com.ingsoftware.contactmanager.repository.ContactTypeRepository;
import com.ingsoftware.contactmanager.service.exception.DuplicateException;
import com.ingsoftware.contactmanager.service.mapping.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private ContactTypeRepository contactTypeRepository;
    @Autowired
    private ContactTypeService contactTypeService;


    public void saveContact(ContactDto contactDto) {
        Contact contact = contactMapper.mapToEntity(contactDto);
        ContactType contactType = contactTypeService.findContactTypeById(contactDto.getContactTypeId());
        contact.setContactType(contactType);
       contactRepository.save(contact);

    }


    public List<ContactDto> getAllContacts() {
        List<Contact> getAllContacts = contactRepository.findAll();
        return contactMapper.contactDtoList(getAllContacts);

    }

    public ContactDto findContact(UUID id) {
        Contact contact = findContactById(id);
        return contactMapper.convertContactToDto(contact);
    }

    public void deleteContact(UUID id) {
        contactRepository.deleteById(id);
    }

    public void updateContact(ContactDto contactDto, UUID id) {
        Contact contact = findContactById(id);
        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setAddress(contactDto.getAddress());
        contact.setPhoneNumber(contactDto.getPhoneNumber());
        contact.setEmail(contactDto.getEmail());

        contactRepository.save(contact);

    }


    public Contact findContactById(UUID contactId) {
        return contactRepository.findById(contactId).orElseThrow(() -> new DuplicateException("Contact not found"));
    }
}


