package com.userhub.userhub.infra.schemas.user;

import com.userhub.userhub.domain.builders.UserBuilder;
import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.domain.entities.user.UserEntity;

// import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.userhub.userhub.infra.schemas.role.RoleSchema;
import com.userhub.userhub.infra.services.BadWordsLoaderService;

import java.io.IOException;

import com.userhub.userhub.infra.schemas.role.RoleMapper;
import com.userhub.userhub.domain.objetcValues.Email;
import com.userhub.userhub.domain.objetcValues.Login;
import com.userhub.userhub.domain.objetcValues.UserName;
import com.userhub.userhub.domain.objetcValues.Password;

@Component
public class UserMapper {

    private static BadWordsLoaderService badWordService;

    public UserMapper(BadWordsLoaderService badWordService) {
        UserMapper.badWordService = badWordService;
    }

    public static UserEntity toDomain(UserSchema schema) {

        List<String> badWords = null;

        try {
            badWords = badWordService.loadBadWords("src/main/resources/config/badwords.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                .login(new Login(
                        new UserName(schema.getUsername(), badWords),
                        new Password(schema.getPassword(), true)))
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
                .username(entity.getLogin().getUsername().getValue())
                .email(entity.getEmail().getvalue())
                .password(entity.getLogin().getPassword().getValue())
                .roles(roles)
                .build();
    }
}