package com.nps.userauth2server.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Role extends BaseModel {
    private String value;
}
