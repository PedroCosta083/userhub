package com.userhub.userhub.domain.builders.auth;

import java.time.LocalDateTime;
import java.util.UUID;

import com.userhub.userhub.domain.entities.auth.AuthEntity;

public class AuthBuilder {

    private UUID id;
    private UUID userId;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    public AuthBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public AuthBuilder userId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public AuthBuilder token(String token) {
        this.token = token;
        return this;
    }

    public AuthBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public AuthBuilder expiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public AuthEntity build() {
        return new AuthEntity(this);
    }

}
