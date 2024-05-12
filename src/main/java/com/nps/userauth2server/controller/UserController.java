package com.nps.userauth2server.controller;

import com.nps.userauth2server.dto.LogOutRequestDto;
import com.nps.userauth2server.dto.LoginRequestDto;
import com.nps.userauth2server.dto.SignUpRequestDto;
import com.nps.userauth2server.dto.UserDto;
import com.nps.userauth2server.exception.UserNotFoundException;
import com.nps.userauth2server.model.Token;
import com.nps.userauth2server.model.User;
import com.nps.userauth2server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserDto signUp(@RequestBody SignUpRequestDto requestDto) {
        User user = userService.signUp(
                requestDto.getEmail(),
                requestDto.getName(),
                requestDto.getPassword(),
                requestDto.getRoles()
        );

        return UserDto.from(user);
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto requestDto) throws UserNotFoundException {
        Token token = userService.login(
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        return token;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogOutRequestDto requestDto) {
        userService.logout(requestDto.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable String token) {
        User user = userService.validateToken(token);

        return UserDto.from(user);
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return null;
    }
}
