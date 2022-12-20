package com.ingsoftware.contactmanager.service;


import com.ingsoftware.contactmanager.controller.dto.ContactDto;
import com.ingsoftware.contactmanager.entity.Contact;
import com.ingsoftware.contactmanager.entity.ContactType;
import com.ingsoftware.contactmanager.entity.User;
import com.ingsoftware.contactmanager.repository.ContactRepository;
import com.ingsoftware.contactmanager.repository.ContactTypeRepository;
import com.ingsoftware.contactmanager.service.exception.DuplicateException;
import com.ingsoftware.contactmanager.service.mapping.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
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
    @Autowired
    private UserService userService;


    public void saveContact(ContactDto contactDto, UUID id) {
        User user = userService.findUserById(id);
        ContactType contactType = contactTypeService.findContactTypeById(contactDto.getContactTypeId());
        Contact contact = contactMapper.mapToEntity(contactDto);

        contact.setContactType(contactType);
        contact.setUser(user);
        contactRepository.save(contact);
    }

    public Page<ContactDto> findAllUserContacts(UUID id, Pageable pageable) {
        var contacts = contactRepository.findAllByUserId(id, pageable).map(contact -> contactMapper.convertContactToDto(contact));
        return contacts;
    }

    public void deleteContact(UUID userId, UUID contactId) {
        contactRepository.delete(contactRepository.findContactById(userId, contactId));
    }

    public void updateContact(ContactDto contactDto, UUID contactId, UUID userId) {
        Contact contact = contactRepository.findContactById(userId, contactId);
        if (contact != null) {
            contact.setFirstName(contactDto.getFirstName());
            contact.setLastName(contactDto.getLastName());
            contact.setAddress(contactDto.getAddress());
            contact.setPhoneNumber(contactDto.getPhoneNumber());
            contact.setEmail(contactDto.getEmail());
            ContactType contactType = contactTypeService.findContactTypeById(contactDto.getContactTypeId());
            contact.setContactType(contactType);

            contactRepository.save(contact);
            return;
        }
        throw new EntityNotFoundException("Not found");
    }

    public Contact findContactById(UUID id) {
        return contactRepository.findById(id).orElseThrow(() -> new DuplicateException("Contact not found"));
    }

    public List<ContactDto> getAllContacts() {
        List<Contact> getAllContacts = contactRepository.findAll();
        return contactMapper.contactDtoList(getAllContacts);

    }

    public Page<ContactDto> searchUserContacts(User user, String name, Pageable pageable) {
        return contactRepository
                .findAllByUserAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(user, name, name, pageable)
                .map(x -> contactMapper.convertContactToDto(x));

    }

    public void addContacts(List<ContactDto> contactDtoList, UUID userId) {
        User user = userService.findUserById(userId);
        var contacts = contactMapper.contactListToDto(contactDtoList);
        contactRepository.saveAll(contacts);

    }
}

