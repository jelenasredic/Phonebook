package com.ingsoftware.contactmanager.service;


import com.ingsoftware.contactmanager.entity.Contact;
import com.ingsoftware.contactmanager.repository.ContactRepository;
import com.ingsoftware.contactmanager.service.exception.ContactDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;


    public Contact findById(UUID id) {
        return contactRepository.findById(id).orElseThrow(() -> new ContactDuplicateException("Contact  not found"));
    }

    public void saveContact(Contact contact) {
        if (contactRepository.existsById(contact.getId())) {
            throw new ContactDuplicateException("Contact already exists");
        }
        contactRepository.save(contact);
    }

    public void updateContact(Contact newContact, UUID id) {
        Contact contact = findById(id);
        contact.setFirstName(newContact.getFirstName());
        contact.setLastName(newContact.getLastName());
        contact.setAddress(newContact.getAddress());
        contact.setPhoneNumber(newContact.getAddress());
        contact.setEmail(newContact.getEmail());
        contactRepository.save(contact);
    }

    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }


    public void deleteContact(UUID id) {
        Contact contact = findById(id);
        contactRepository.delete(contact);
    }
}
