package com.userhub.userhub.infra.schemas.user;

import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.domain.entities.user.UserEntity;

// import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.userhub.userhub.infra.schemas.role.RoleSchema;
import com.userhub.userhub.infra.schemas.role.RoleMapper;
import com.userhub.userhub.application.builders.user.UserBuilder;
import com.userhub.userhub.domain.objetcValues.Email;
import com.userhub.userhub.domain.objetcValues.Login;
import com.userhub.userhub.domain.objetcValues.Password;

public class UserMapper {
    public static UserEntity toDomain(UserSchema schema) {

        Set<RoleEntity> roles = null;
        if (schema.getRoles() != null) {
            roles = schema.getRoles().stream()
                    .map(RoleMapper::toDomain)
                    .collect(Collectors.toSet());
        }

        UserEntity user = new UserBuilder()
                .id(schema.getId())
                .name(schema.getName())
                .active(schema.getActive())
                .createdAt(schema.getCreatedAt())
                .updatedAt(schema.getUpdatedAt())
                .deactivatedAt(schema.getDeactivatedAt())
                .birthday(schema.getBirthday())
                .login(new Login(schema.getUsername(), new Password(schema.getPassword(), true)))
                .email(new Email(schema.getEmail()))
                .roles(roles)
                .build();
        return user;
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
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deactivatedAt(entity.getDeactivatedAt())
                .birthday(entity.getBirthday())
                .username(entity.getLogin().getUsername())
                .email(entity.getEmail().getvalue())
                .password(entity.getLogin().getPassword().getValue())
                .roles(roles)
                .build();
    }
}