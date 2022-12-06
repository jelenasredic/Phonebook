package com.ingsoftware.contactmanager.service;

import com.ingsoftware.contactmanager.dto.ContactDto;
import com.ingsoftware.contactmanager.dto.ContactTypeDto;
import com.ingsoftware.contactmanager.dto.UserDto;
import com.ingsoftware.contactmanager.entity.Contact;
import com.ingsoftware.contactmanager.entity.ContactType;
import com.ingsoftware.contactmanager.entity.User;
import com.ingsoftware.contactmanager.exception.ContactException;
import com.ingsoftware.contactmanager.exception.ContactTypeException;
import com.ingsoftware.contactmanager.exception.UserException;
import com.ingsoftware.contactmanager.repository.ContactRepository;
import com.ingsoftware.contactmanager.repository.ContactTypeRepository;
import com.ingsoftware.contactmanager.repository.UserRepository;
import com.ingsoftware.contactmanager.service.mapping.ContactMapper;
import com.ingsoftware.contactmanager.service.mapping.ContactTypeMapper;
import com.ingsoftware.contactmanager.service.mapping.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    private ContactTypeRepository contactTypeRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactTypeMapper contactTypeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ContactMapper contactMapper;

    public void insertContactType (ContactTypeDto dto){
        if(contactTypeRepository.existsByTypeIgnoreCase(dto.getType())){
            throw new ContactTypeException("Type already exists");
        }
        ContactType type=contactTypeMapper.mapToEntity(dto);
        contactTypeRepository.save(type);
    }
    public void addUser (UserDto dto){
        if(userRepository.existsByEmailIgnoreCase(dto.getEmail())){
            throw new UserException("User already exist!");
        }
        User user=userMapper.mapToEnity(dto);
        userRepository.save(user);
    }

    public void addNewContact (ContactDto dto){
        if(contactRepository.existsByEmailIgnoreCase(dto.getEmail())){
            throw new ContactException("Contact already exists!");
        }
        Contact contact=contactMapper.mapToEntity(dto);
        contactRepository.save(contact);
    }
}
