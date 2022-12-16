package com.ingsoftware.contactmanager.controller;

import com.ingsoftware.contactmanager.controller.dto.ContactDto;
import com.ingsoftware.contactmanager.entity.SecurityUser;
import com.ingsoftware.contactmanager.service.ContactService;
import com.ingsoftware.contactmanager.service.ContactTypeService;
import com.ingsoftware.contactmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
    private ContactTypeService contactTypeService;

    @PostMapping("/contacts")
    public ResponseEntity<ContactDto> saveContact(@Valid @RequestBody ContactDto contactDto, @AuthenticationPrincipal SecurityUser securityUser) {

        contactService.saveContact(contactDto, securityUser.getId());
        return new ResponseEntity<ContactDto>(contactDto, HttpStatus.OK);
    }

    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<ContactDto> deleteContact(@PathVariable UUID contactId, @AuthenticationPrincipal SecurityUser securityUser) {
        contactService.deleteContact(securityUser.getId(), contactId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/contacts/{contactId}")
    public ResponseEntity<ContactDto> updateContact(@PathVariable(name = "contactId") UUID contactId, @Valid @RequestBody ContactDto contactDto, @AuthenticationPrincipal SecurityUser securityUser) {
        contactService.updateContact(contactDto, contactId, securityUser.getId());
        return new ResponseEntity<ContactDto>(contactDto, HttpStatus.OK);
    }

    @GetMapping("/contact")
    public ResponseEntity<?> getAllContacts(@AuthenticationPrincipal SecurityUser securityUser, Pageable pageable) {
        var user = userService.findUserById(securityUser.getId());
        var contacts = contactService.findAllUserContacts(securityUser.getId(), pageable);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/contacts")
    public ResponseEntity<List<ContactDto>> getAllContacts() {
        List<ContactDto> getAllContacts = contactService.getAllContacts();
        return ResponseEntity.status(HttpStatus.OK).body(getAllContacts);
    }

    @GetMapping("/contact/search")
    public ResponseEntity<Page<ContactDto>> searchContacts(@AuthenticationPrincipal SecurityUser securityUser, @RequestParam("name") String name, Pageable pageable) {
        var user = userService.findUserById(securityUser.getId());
        return ResponseEntity.ok(contactService.searchUserContacts(user, name, pageable));

    }

}

