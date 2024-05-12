package com.nps.userauth2server.dto;

import com.nps.userauth2server.model.Role;
import com.nps.userauth2server.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String name;
    private String email;
    private List<Role> roles;

    public static UserDto from(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}
