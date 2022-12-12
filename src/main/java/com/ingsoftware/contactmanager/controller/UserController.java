package com.ingsoftware.contactmanager.controller;

import com.ingsoftware.contactmanager.controller.dto.ContactDto;
import com.ingsoftware.contactmanager.entity.SecurityUser;
import com.ingsoftware.contactmanager.service.ContactService;
import com.ingsoftware.contactmanager.service.ContactTypeService;
import com.ingsoftware.contactmanager.service.UserService;
import com.ingsoftware.contactmanager.service.mapping.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.PrivilegedAction;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private ContactTypeService contactTypeService;



    @PostMapping("/contacts")
    public ResponseEntity<ContactDto> saveContact(@Valid @RequestBody ContactDto contactDto, @AuthenticationPrincipal SecurityUser securityUser) {
        var user = userService.findUserById(securityUser.getId());
        var contact = contactMapper.mapToEntity(contactDto);
        contact.setUser(user);
        contactService.saveContact(contactDto);
        return new ResponseEntity<ContactDto>(contactDto, HttpStatus.OK);
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<ContactDto> deleteContact(@PathVariable UUID id, @AuthenticationPrincipal SecurityUser securityUser) {

        contactService.deleteContact(id);
        return new ResponseEntity<ContactDto>(HttpStatus.OK);
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<ContactDto> updateContact(@Valid @RequestBody ContactDto contactDto, @PathVariable UUID id) {
        contactService.updateContact(contactDto, id);
        return new ResponseEntity<ContactDto>(contactDto, HttpStatus.OK);

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
