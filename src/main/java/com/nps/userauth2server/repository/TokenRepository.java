package com.nps.userauth2server.repository;

import com.nps.userauth2server.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    @Override
    Token save(Token token);

    Optional<Token> findByValueAndDeleted(String tokenValue, boolean deleted);

    /*
    select * from tokens where value = tokenValue and deleted = false and expiry_date > time.
     */
    Optional<Token> findByValueAndDeletedAndExpiryAtGreaterThan(String tokenValue, boolean deleted, LocalDateTime currentTime);
}
