package com.ingsoftware.contactmanager.service;

import com.ingsoftware.contactmanager.controller.dto.ContactTypeDto;
import com.ingsoftware.contactmanager.entity.ContactType;
import com.ingsoftware.contactmanager.repository.ContactTypeRepository;
import com.ingsoftware.contactmanager.service.exception.DuplicateException;
import com.ingsoftware.contactmanager.service.mapping.ContactTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactTypeService {
    @Autowired
    private ContactTypeRepository contactTypeRepository;
    @Autowired
    private ContactTypeMapper contactTypeMapper;

    public void saveContactType(ContactTypeDto contactTypeDto) {
        if (contactTypeRepository.existsByTypeIgnoreCase(contactTypeDto.getType())) {
            throw new DuplicateException("Contact type already exists");
        }
        ContactType type = contactTypeMapper.mapToEntity(contactTypeDto);
        contactTypeRepository.save(type);
    }

    public List<ContactTypeDto> getAllContactTypes() {
        List<ContactType> getAllContactTypes = contactTypeRepository.findAll();
        return contactTypeMapper.contactTypeDtoList(getAllContactTypes);
    }

    public ContactTypeDto findContactType(UUID id) {
        ContactType contactType = findContactTypeById(id);
        return contactTypeMapper.convertContactTypeToDto(contactType);
    }

    public void deleteContactType(UUID id) {
        contactTypeRepository.deleteById(id);
    }

    public void updateContactType(ContactTypeDto contactTypeDto, UUID id) {
        ContactType contactType = findContactTypeById(id);
        contactType.setType(contactTypeDto.getType());
        contactType.setDescription(contactTypeDto.getDescription());
        contactTypeRepository.save(contactType);
    }

    public ContactType findContactTypeById(UUID id) {
        return contactTypeRepository.findById(id).orElseThrow(() -> new DuplicateException("Contact type not found"));
    }
}
