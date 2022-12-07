package com.ingsoftware.contactmanager.service;

import com.ingsoftware.contactmanager.entity.ContactType;
import com.ingsoftware.contactmanager.repository.ContactTypeRepository;
import com.ingsoftware.contactmanager.service.exception.ContactTypeDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactTypeService {
    @Autowired
    private ContactTypeRepository contactTypeRepository;

    public ContactType findById(UUID id) {
        return contactTypeRepository.findById(id).orElseThrow(() -> new ContactTypeDuplicateException("Contact type not found"));
    }

    public void saveContactType(ContactType contactType) {
        if (contactTypeRepository.existsByTypeIgnoreCase(contactType.getType())) {
            throw new ContactTypeDuplicateException("Contact Type already exists");
        }
        contactTypeRepository.save(contactType);
    }


    public void updateContactType(ContactType newContactType, UUID id){
        ContactType contactType= findById(id);
        contactType.setType(newContactType.getType());
        contactType.setDescription(newContactType.getDescription());
        contactTypeRepository.save(contactType);
    }
    public List<ContactType> getAllContactTypes() {
        return contactTypeRepository.findAll();
    }
    public void deleteContactType(UUID id) {
        ContactType contactType= findById(id);
        contactTypeRepository.delete(contactType);
    }
}
