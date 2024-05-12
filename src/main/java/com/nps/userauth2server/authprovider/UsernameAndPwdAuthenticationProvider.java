package com.nps.userauth2server.authprovider;

import com.nps.userauth2server.model.Role;
import com.nps.userauth2server.model.User;
import com.nps.userauth2server.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
public class UsernameAndPwdAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UsernameAndPwdAuthenticationProvider(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Optional<User> userOptional = userRepository.findByEmail(username);
        if(userOptional.isEmpty()) {
            throw new BadCredentialsException("User does not exist");
        }
        User user = userOptional.get();
        if (passwordEncoder.matches(password, user.getPassword())) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (Role role: user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getValue()));
            }
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        } else {
            throw new BadCredentialsException("Invalid password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
