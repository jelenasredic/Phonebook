package com.ingsoftware.contactmanager.controller;

import com.ingsoftware.contactmanager.dto.ContactDto;
import com.ingsoftware.contactmanager.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private ContactService contactService;
    @PostMapping("/contact")
    public ResponseEntity <ContactDto> addNewContact (@RequestBody @Valid ContactDto dto){
        contactService.addNewContact(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
