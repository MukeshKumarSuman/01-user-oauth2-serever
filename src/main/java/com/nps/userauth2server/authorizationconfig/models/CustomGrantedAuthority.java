package com.nps.userauth2server.authorizationconfig.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nps.userauth2server.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
@Data
@NoArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {
    private String authority;

    public CustomGrantedAuthority(Role role) {
        this.authority = role.getValue();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
