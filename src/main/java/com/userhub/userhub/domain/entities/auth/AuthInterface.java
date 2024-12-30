package com.userhub.userhub.domain.entities.auth;

import java.time.LocalDateTime;
import java.util.UUID;

public interface AuthInterface {

    UUID getId();

    UUID getUserId();

    String getToken();

    String getIpAddress();

    String getUserAgent();

    LocalDateTime getCreatedAt();

    LocalDateTime getExpiresAt();

    boolean isExpired();

}
