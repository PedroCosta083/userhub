package com.userhub.userhub.infra.schemas.auth;

import com.userhub.userhub.domain.builders.auth.AuthBuilder;
import com.userhub.userhub.domain.entities.auth.AuthEntity;

public class AuthMapper {

    public static AuthSchema toSchema(AuthEntity authEntity) {
        return AuthSchema.builder()
                .id(authEntity.getId())
                .userId(authEntity.getUserId())
                .token(authEntity.getToken())
                .createdAt(authEntity.getCreatedAt())
                .expiresAt(authEntity.getExpiresAt())
                .build();
    }

    public static AuthEntity toEntity(AuthSchema authSchema) {
        AuthEntity auth = new AuthBuilder()
                .id(authSchema.getId())
                .userId(authSchema.getUserId())
                .token(authSchema.getToken())
                .createdAt(authSchema.getCreatedAt())
                .expiresAt(authSchema.getExpiresAt())
                .build();
        return auth;
    }

}
