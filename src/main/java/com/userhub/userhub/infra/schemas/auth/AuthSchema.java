package com.userhub.userhub.infra.schemas.auth;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthSchema {
    @Id
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(updatable = false, nullable = false)

    private UUID userId;

    private String token;

    private String ipAddress;

    private String userAgent;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

}
