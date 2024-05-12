package com.nps.userauth2server.dto;

import com.nps.userauth2server.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class SignUpRequestDto {
    private String email;
    private String name;
    private String password;
    private List<Role> roles;
}
