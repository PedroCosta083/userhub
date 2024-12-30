package com.userhub.userhub.infra.repository.auth;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.userhub.userhub.domain.entities.auth.AuthEntity;
import com.userhub.userhub.domain.entities.auth.AuthRepositoryInterface;
import com.userhub.userhub.infra.schemas.auth.AuthMapper;
import com.userhub.userhub.infra.schemas.auth.AuthSchema;

@Repository
public class AuthRepository implements AuthRepositoryInterface {

    private final JpaAuthRepository jpaAuthRepository;

    public AuthRepository(JpaAuthRepository jpaAuthRepository) {
        this.jpaAuthRepository = jpaAuthRepository;
    }

    @Override
    public void save(AuthEntity authEntity) {
        AuthSchema authSchema = AuthMapper.toSchema(authEntity);
        this.jpaAuthRepository.save(authSchema);
    }

    @Override
    public Optional<AuthEntity> findByToken(String token) {
        AuthSchema authSchema = this.jpaAuthRepository.findByToken(token);
        return Optional.ofNullable(AuthMapper.toEntity(authSchema));
    }

    @Override
    public Optional<AuthEntity> findByUserId(UUID userId) {
        AuthSchema authSchema = this.jpaAuthRepository.findByUserId(userId);
        return Optional.ofNullable(AuthMapper.toEntity(authSchema));
    }

    @Override
    public void deleteByToken(String token) {
        this.jpaAuthRepository.deleteByToken(token);
    }

    @Override
    public void deleteExpiredTokens() {
        this.jpaAuthRepository.deleteExpiredTokens();
    }

}
