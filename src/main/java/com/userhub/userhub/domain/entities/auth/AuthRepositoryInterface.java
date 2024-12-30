package com.userhub.userhub.domain.entities.auth;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepositoryInterface {

    void save(AuthEntity authEntity);

    Optional<AuthEntity> findByToken(String token);

    Optional<AuthEntity> findByUserId(UUID userId);

    void deleteByToken(String token);

    void deleteExpiredTokens();

}
