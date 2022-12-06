package com.ingsoftware.contactmanager.service.mapping;

import com.ingsoftware.contactmanager.dto.UserDto;
import com.ingsoftware.contactmanager.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToEnity(UserDto userDto) {
        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        return user;
    }

}
