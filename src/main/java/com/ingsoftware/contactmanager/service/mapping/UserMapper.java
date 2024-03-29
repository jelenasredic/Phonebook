package com.ingsoftware.contactmanager.service.mapping;

import com.ingsoftware.contactmanager.controller.dto.UserDto;
import com.ingsoftware.contactmanager.entity.Role;
import com.ingsoftware.contactmanager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User mapToEntity(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRole(Role.USER);

        return user;
    }
    public UserDto convertUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(passwordEncoder.encode(user.getPassword()));
        userDto.setEmail(user.getEmail());
        userDto.setRole(Role.USER);
        return userDto;
    }
    public List<UserDto> userDtoList(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (var user : userList) {
            var userDto = convertUserToDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

}
