package com.ingsoftware.contactmanager.controller;

import com.ingsoftware.contactmanager.controller.dto.ContactTypeDto;
import com.ingsoftware.contactmanager.controller.dto.UserDto;
import com.ingsoftware.contactmanager.entity.ContactType;
import com.ingsoftware.contactmanager.entity.User;
import com.ingsoftware.contactmanager.service.ContactService;
import com.ingsoftware.contactmanager.service.ContactTypeService;
import com.ingsoftware.contactmanager.service.UserService;
import com.ingsoftware.contactmanager.service.mapping.ContactTypeMapper;
import com.ingsoftware.contactmanager.service.mapping.UserMapper;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class AdminController {
    @Autowired
    private ContactTypeService contactTypeService;

    @Autowired
    private UserService userService;
@Autowired
private ContactTypeMapper contactTypeMapper;
@Autowired
    private UserMapper userMapper;

    @PostMapping("/contact-type/{id}")
    public ResponseEntity<ContactTypeDto> saveContactType(@RequestBody @Valid ContactTypeDto dto) {
        var type=contactTypeMapper.mapToEntity(dto);
        contactTypeService.saveContactType(type);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("/contact-type/{id}")
    public void deleteContactType(@PathVariable UUID id) {
        contactTypeService.deleteContactType(id);
    }

    @PutMapping("/contact-type/{id}")
    public ResponseEntity<ContactTypeDto> updateContactType(@PathVariable(name = "id") UUID id, @RequestBody ContactTypeDto dto) {
        var oldContactType = contactTypeService.findById(id);
        oldContactType.setType(dto.getType());
        oldContactType.setDescription(dto.getDescription());
        contactTypeService.saveContactType(oldContactType);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    @GetMapping("/contact-type/{id}")
    public List<ContactType> allContactType(){
    return contactTypeService.getAllContactTypes();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/users")
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserDto dto) {
        var user = userMapper.mapToEnity(dto);
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("/users")
    public void deleteUser(@PathVariable UUID id) {

        userService.deleteUser(id);
    }

    @PutMapping("/users")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") UUID id, @RequestBody UserDto dto) {
        var oldUser = userService.findById(id);
        oldUser.setFirstName(dto.getFirstName());
        oldUser.setLastName(dto.getLastName());
        oldUser.setPassword(dto.getPassword());
        oldUser.setEmail(dto.getEmail());
        oldUser.setRole(dto.getRole());
        userService.saveUser(oldUser);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    @GetMapping("/users")
    public List<User> allUser(){
        return userService.getAllUsers();
    }

}

