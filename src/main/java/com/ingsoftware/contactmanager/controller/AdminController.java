package com.ingsoftware.contactmanager.controller;

import com.ingsoftware.contactmanager.controller.dto.ContactDto;
import com.ingsoftware.contactmanager.controller.dto.ContactTypeDto;
import com.ingsoftware.contactmanager.controller.dto.UserDto;
import com.ingsoftware.contactmanager.entity.SecurityUser;
import com.ingsoftware.contactmanager.service.ContactTypeService;
import com.ingsoftware.contactmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ContactTypeService contactTypeService;

    @Autowired
    private UserService userService;

    @PostMapping("/contact-type")
    public ResponseEntity<ContactTypeDto> saveContactType(@Valid @RequestBody ContactTypeDto contactTypeDto) {
        contactTypeService.saveContactType(contactTypeDto);
        return new ResponseEntity<ContactTypeDto>(contactTypeDto, HttpStatus.OK);
    }

    @DeleteMapping("/contact-type/{id}")
    public ResponseEntity<ContactTypeDto> deleteContactType(@PathVariable UUID id) {
        contactTypeService.deleteContactType(id);
        return new ResponseEntity<ContactTypeDto>(HttpStatus.OK);
    }

    @PutMapping("/contact-type/{id}")
    public ResponseEntity<ContactTypeDto> updateContactType(@Valid @RequestBody ContactTypeDto contactTypeDto, @PathVariable UUID id) {
        contactTypeService.updateContactType(contactTypeDto, id);
        return new ResponseEntity<ContactTypeDto>(contactTypeDto, HttpStatus.OK);

    }

    @GetMapping("/contact-type/{id}")
    public ResponseEntity<ContactTypeDto> findContactType(@PathVariable UUID id) {
        ContactTypeDto existingContactType = contactTypeService.findContactType(id);
        return new ResponseEntity<ContactTypeDto>(existingContactType, HttpStatus.OK);
    }

    @GetMapping("/contact-type")
    public ResponseEntity<List<ContactTypeDto>> getAllContactTypes() {
        List<ContactTypeDto> getAllContactTypes = contactTypeService.getAllContactTypes();
        return ResponseEntity.status(HttpStatus.OK).body(getAllContactTypes);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/users")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable UUID id) {
        UserDto existingUser = userService.findUser(id);
        userService.deleteUser(id);
        return new ResponseEntity<UserDto>(HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable UUID id) {
        userService.updateUser(userDto, id);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> findUser(@PathVariable UUID id) {
        UserDto existingUser = userService.findUser(id);
        return new ResponseEntity<UserDto>(existingUser, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> getAllUsers = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(getAllUsers);
    }
}


