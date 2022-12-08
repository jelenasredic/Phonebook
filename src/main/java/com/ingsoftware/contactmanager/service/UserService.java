package com.ingsoftware.contactmanager.service;

import com.ingsoftware.contactmanager.controller.dto.ContactTypeDto;
import com.ingsoftware.contactmanager.controller.dto.UserDto;
import com.ingsoftware.contactmanager.entity.ContactType;
import com.ingsoftware.contactmanager.entity.Role;
import com.ingsoftware.contactmanager.entity.User;
import com.ingsoftware.contactmanager.repository.UserRepository;
import com.ingsoftware.contactmanager.service.exception.DuplicateException;
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

    public void saveUser(UserDto userDto) {
        if (userRepository.existsByEmailIgnoreCase(userDto.getEmail())) {
            throw new DuplicateException("User already exists");
        }
        User user = userMapper.mapToEntity(userDto);
        userRepository.save(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> getAllUsers = userRepository.findAll();
        return userMapper.userDtoList(getAllUsers);

    }

    public UserDto findUser(UUID id) {
        User user = findUserById(id);
        return userMapper.convertUserToDto(user);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public void updateUser(UserDto userDto, UUID id) {
        User user = findUserById(id);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    public User findUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new DuplicateException("User not found"));
    }
}
