package com.nps.userauth2server.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
