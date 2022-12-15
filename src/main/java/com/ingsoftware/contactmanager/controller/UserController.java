package com.ingsoftware.contactmanager.controller;

import com.ingsoftware.contactmanager.controller.dto.ContactDto;
import com.ingsoftware.contactmanager.controller.dto.CustomPageDto;
import com.ingsoftware.contactmanager.entity.SecurityUser;
import com.ingsoftware.contactmanager.service.ContactService;
import com.ingsoftware.contactmanager.service.ContactTypeService;
import com.ingsoftware.contactmanager.service.UserService;
import com.ingsoftware.contactmanager.service.mapping.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping("/user")
public class
UserController {
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

        contactService.saveContact(contactDto, securityUser.getId());
        return new ResponseEntity<ContactDto>(contactDto, HttpStatus.OK);
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<ContactDto> deleteContact(@PathVariable UUID id, @AuthenticationPrincipal SecurityUser securityUser) {
        var contact = contactService.findContactById(id);
        var user = userService.findUserById(securityUser.getId());
        if (securityUser.getId().equals(contact.getUser().getId())) {
            contactService.deleteContact(id);
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<ContactDto> updateContact(@PathVariable(name = "id") UUID id,@Valid @RequestBody ContactDto contactDto, @AuthenticationPrincipal SecurityUser securityUser) {
        var contact = contactService.findContactById(id);
        if (securityUser.getId().equals(contact.getUser().getId())) {
            contactService.updateContact(contactDto, id);
        }
        return new ResponseEntity<ContactDto>(contactDto, HttpStatus.OK);
    }


    @GetMapping("/contact")
    public ResponseEntity<?> getAllContacts(@AuthenticationPrincipal SecurityUser securityUser, Pageable pageable) {
        var user = userService.findUserById(securityUser.getId());
        var contacts=contactService.findAllUserContacts(securityUser.getId(), pageable);
        var customPage = new CustomPageDto<>(contacts.getContent(), pageable.getPageNumber(), pageable.getPageSize(), contacts.getTotalPages());
       return ResponseEntity.ok(customPage);
    }
   }
