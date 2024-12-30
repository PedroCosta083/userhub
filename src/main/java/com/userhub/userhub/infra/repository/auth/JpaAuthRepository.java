package com.userhub.userhub.infra.repository.auth;

import java.util.UUID;

import com.userhub.userhub.infra.schemas.auth.AuthSchema;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaAuthRepository extends JpaRepository<AuthSchema, UUID> {
    AuthSchema findByUserId(UUID userId);

    AuthSchema findByToken(String token);

    void deleteByToken(String token);

    @Modifying
    @Query("DELETE FROM AuthSchema a WHERE a.expiresAt < CURRENT_DATE")
    void deleteExpiredTokens();

}
