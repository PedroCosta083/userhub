package com.userhub.userhub.domain.entities.auth;

import java.time.LocalDateTime;
import java.util.UUID;

import com.userhub.userhub.domain.builders.auth.AuthBuilder;

public class AuthEntity implements AuthInterface {

    private final UUID id;
    private final UUID userId;
    private final String token;
    private String ipAddress;
    private String userAgent;
    private final LocalDateTime createdAt;
    private final LocalDateTime expiresAt;

    public AuthEntity(AuthBuilder builder) {
        this.id = builder.getId();
        this.userId = builder.getUserId();
        this.token = builder.getToken();
        this.createdAt = builder.getCreatedAt();
        this.expiresAt = builder.getExpiresAt();
        validate();
    }

    private void validate() {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token cannot be null or empty");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("Created At cannot be null");
        }
        if (expiresAt == null) {
            throw new IllegalArgumentException("Expires At cannot be null");
        }
        if (expiresAt.isBefore(createdAt)) {
            throw new IllegalArgumentException("Expires At cannot be before Created At");
        }
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

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    // Método para verificar se o token está expirado
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

}
