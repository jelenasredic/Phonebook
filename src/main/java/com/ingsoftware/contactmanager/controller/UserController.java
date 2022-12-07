package com.ingsoftware.contactmanager.controller;

import com.ingsoftware.contactmanager.controller.dto.ContactDto;
import com.ingsoftware.contactmanager.controller.dto.ContactTypeDto;
import com.ingsoftware.contactmanager.entity.Contact;
import com.ingsoftware.contactmanager.entity.ContactType;
import com.ingsoftware.contactmanager.entity.User;
import com.ingsoftware.contactmanager.service.ContactService;
import com.ingsoftware.contactmanager.service.mapping.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.PrivilegedAction;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactMapper contactMapper;

    @PostMapping("/contacts")
    public ResponseEntity<ContactDto> saveContact(@RequestBody @Valid ContactDto dto) {
        var contact =contactMapper.mapToEntity(dto);
        contactService.saveContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("/contacts")
    public void deleteContact(@PathVariable UUID id) {
        contactService.deleteContact(id);
    }

    @PutMapping("/contacts")
    public ResponseEntity<ContactDto> updateContact(@PathVariable(name = "id") UUID id, @RequestBody ContactDto dto) {
        var oldContact = contactService.findById(id);
        oldContact.setFirstName(dto.getFirstName());
        oldContact.setLastName(dto.getLastName());
        oldContact.setAddress(dto.getAddress());
        oldContact.setPhoneNumber(dto.getPhoneNumber());
        oldContact.setEmail(dto.getEmail());
        contactService.saveContact(oldContact);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    @GetMapping("/contacts")
    public List<Contact> allContact(){
        return contactService.getAllContact();
    }
}
