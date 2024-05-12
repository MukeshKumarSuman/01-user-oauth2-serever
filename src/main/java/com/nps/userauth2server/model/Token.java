package com.nps.userauth2server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Token extends BaseModel {
    private String value;
    @ManyToOne
    private User user;
    private LocalDateTime expiryAt;
}
