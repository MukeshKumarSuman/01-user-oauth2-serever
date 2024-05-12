package com.nps.userauth2server.authorizationconfig.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;

/**
 * Client entity, which is used to persist information
 * mapped from the RegisteredClient domain object
 */
@Data
@Entity
@Table(name = "`client`")
public class Client {
    @Id
    private String id;
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    @Column(length = 1000)
    @Lob
    private String clientAuthenticationMethods;
    @Column(length = 1000)
    @Lob
    private String authorizationGrantTypes;
    @Column(length = 1000)
    @Lob
    private String redirectUris;
    @Column(length = 1000)
    @Lob
    private String postLogoutRedirectUris;
    @Column(length = 1000)
    @Lob
    private String scopes;
    @Column(length = 2000)
    @Lob
    private String clientSettings;
    @Column(length = 2000)
    @Lob
    private String tokenSettings;
}
