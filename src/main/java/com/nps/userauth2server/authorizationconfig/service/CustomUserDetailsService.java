package com.nps.userauth2server.authorizationconfig.service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nps.userauth2server.authorizationconfig.models.CustomUserDetails;
import com.nps.userauth2server.model.User;
import com.nps.userauth2server.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@JsonDeserialize
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User with email: " + username + " doesn't exist");
        }

        return new CustomUserDetails(optionalUser.get());
    }
}
