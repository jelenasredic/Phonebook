package com.ingsoftware.contactmanager.service;

import com.ingsoftware.contactmanager.controller.dto.UserDto;
import com.ingsoftware.contactmanager.entity.ContactType;
import com.ingsoftware.contactmanager.entity.User;
import com.ingsoftware.contactmanager.repository.UserRepository;
import com.ingsoftware.contactmanager.service.exception.ContactTypeDuplicateException;
import com.ingsoftware.contactmanager.service.exception.UserDuplicateException;
import com.ingsoftware.contactmanager.service.mapping.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserDuplicateException("User not found"));
    }



    public void saveUser(User user) {
        if (userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new UserDuplicateException("User already exists");
        }
        userRepository.save(user);
    }

    public void updateUser(User newUser, UUID id) {
        User user = findById(id);
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setPassword(newUser.getPassword());
        user.setEmail(newUser.getEmail());
        user.setRole(newUser.getRole());
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public void deleteUser(UUID id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}


