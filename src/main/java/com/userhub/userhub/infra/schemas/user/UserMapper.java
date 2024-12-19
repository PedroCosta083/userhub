package com.userhub.userhub.infra.schemas.user;

import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.domain.entities.user.UserEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.userhub.userhub.infra.schemas.role.RoleSchema;
import com.userhub.userhub.infra.schemas.role.RoleMapper;

public class UserMapper {
    public static UserEntity toDomain(UserSchema schema) {

        Set<RoleEntity> roles = schema.getRoles().stream()
                .map(RoleMapper::toDomain)
                .collect(Collectors.toSet());

        return new UserEntity(
                schema.getId(),
                schema.getName(),
                schema.getActive(),
                schema.getCreatedAt(),
                schema.getUpdatedAt(),
                schema.getDeactivatedAt(),
                schema.getBirthday(),
                schema.getLogin(),
                schema.getEmail(),
                schema.getPassword(),
                roles);
    }

    public static List<UserEntity> toDomainList(List<UserSchema> schemas) {
        return schemas.stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static UserSchema toSchema(UserEntity entity) {
        List<RoleSchema> roles = null;
        if (entity.getRoles() != null) {
            roles = entity.getRoles().stream()
                    .map(RoleMapper::toSchema)
                    .collect(Collectors.toList());
        }
        return UserSchema.builder()
                .id(entity.getId())
                .name(entity.getName())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAT())
                .updatedAt(entity.getUpdatedAt())
                .deactivatedAt(entity.getDeactivatedAt())
                .birthday(entity.getBirthday())
                .login(entity.getLogin())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .roles(roles)
                .build();
    }
}