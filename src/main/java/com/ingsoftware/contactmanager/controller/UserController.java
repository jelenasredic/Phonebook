package com.ingsoftware.contactmanager.controller;

import com.ingsoftware.contactmanager.controller.dto.ContactDto;
import com.ingsoftware.contactmanager.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/contacts")
    public ResponseEntity<ContactDto> saveContact(@Valid @RequestBody ContactDto contact) {
        contactService.saveContact(contact);
        return new ResponseEntity<ContactDto>(contact, HttpStatus.OK);
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<ContactDto> deleteContact(@PathVariable UUID id) {
        ContactDto existingContact = contactService.findContact(id);
        contactService.deleteContact(id);
        return new ResponseEntity<ContactDto>(HttpStatus.OK);
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<ContactDto> updateContact(@Valid @RequestBody ContactDto contact, @PathVariable UUID id) {
        ContactDto existingContact = contactService.findContact(id);
        contactService.updateContact(contact, id);
        return new ResponseEntity<ContactDto>(contact, HttpStatus.OK);

    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<ContactDto> findContact(@PathVariable UUID id) {
        ContactDto existingContact = contactService.findContact(id);
        return new ResponseEntity<ContactDto>(existingContact, HttpStatus.OK);
    }

    @GetMapping("/contacts")
    public ResponseEntity<List<ContactDto>> getAllContacts() {
        List<ContactDto> getAllContacts = contactService.getAllContacts();
        return ResponseEntity.status(HttpStatus.OK).body(getAllContacts);
    }
}
