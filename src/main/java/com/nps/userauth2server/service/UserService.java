package com.nps.userauth2server.service;

import com.nps.userauth2server.exception.UserNotFoundException;
import com.nps.userauth2server.model.Role;
import com.nps.userauth2server.model.Token;
import com.nps.userauth2server.model.User;
import com.nps.userauth2server.repository.RoleRepository;
import com.nps.userauth2server.repository.TokenRepository;
import com.nps.userauth2server.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final RoleRepository roleRepository;

    UserService(PasswordEncoder passwordEncoder,
                UserRepository userRepository,
                TokenRepository tokenRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.roleRepository = roleRepository;
    }

    public User signUp(String email,
                       String name,
                       String password, List<Role> roles) {

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        user.setEmailVerified(true);

        roleRepository.saveAll(roles);
        //save the user object to the DB.
        return userRepository.save(user);
    }

    public Token login(String email, String password) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with email " + email + " doesn't exist");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            //Throw some exception.
            return null;
        }

        //Login successful, generate a Token.
        Token token = generateToken(user);
        Token savedToken = tokenRepository.save(token);

        return savedToken;
    }

    private Token generateToken(User user) {
        LocalDate currentDate = LocalDate.now();
        LocalDate thirtyDaysLater = currentDate.plusDays(30);
        // Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDateTime expiryDate = LocalDateTime.now().plusDays(30);

        Token token = new Token();
        token.setExpiryAt(expiryDate);
        //128 character alphanumeric string.
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        token.setUser(user);
        return token;
    }

    public void logout(String tokenValue) {
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeleted(tokenValue, false);

        if (optionalToken.isEmpty()) {
            //Throw new Exception
            return;
        }

        Token token = optionalToken.get();
        token.setDeleted(true);
        tokenRepository.save(token);
    }

    public User validateToken(String token) {
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedAndExpiryAtGreaterThan(token, false, LocalDateTime.now());

        if (optionalToken.isEmpty()) {
            //Throw new Exception
            return null;
        }

        return optionalToken.get().getUser();
    }
}
