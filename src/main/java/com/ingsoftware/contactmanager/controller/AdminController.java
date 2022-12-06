package com.ingsoftware.contactmanager.controller;

import com.ingsoftware.contactmanager.dto.ContactTypeDto;
import com.ingsoftware.contactmanager.dto.UserDto;
import com.ingsoftware.contactmanager.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AdminController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/contact-type")
    public ResponseEntity <ContactTypeDto> insertContactType (@RequestBody @Valid ContactTypeDto dto){
        contactService.insertContactType(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
@PostMapping("/user")
    public ResponseEntity<UserDto> addUser (@RequestBody @Valid UserDto dto){
        contactService.addUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);

}

}
