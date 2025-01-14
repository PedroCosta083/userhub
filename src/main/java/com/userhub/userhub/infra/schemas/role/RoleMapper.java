package com.userhub.userhub.infra.schemas.role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.userhub.userhub.domain.builders.RoleBuilder;
import com.userhub.userhub.domain.entities.role.RoleEntity;

public class RoleMapper {
    public static RoleEntity toDomain(RoleSchema schema) {
        RoleEntity role = new RoleBuilder()
                .id(schema.getId())
                .name(schema.getName())
                .active(schema.getActive())
                .createdAt(schema.getCreatedAt())
                .updatedAt(schema.getUpdatedAt())
                .deactivatedAt(schema.getDeactivatedAt())
                .build();
        return role;
    }

    public static List<RoleEntity> toDomainList(List<RoleSchema> schemas) {
        return schemas.stream()
                .map(RoleMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static Set<RoleEntity> toDomainSet(List<RoleSchema> schemas) {
        return schemas.stream()
                .map(RoleMapper::toDomain)
                .collect(Collectors.toSet());
    }

    public static RoleSchema toSchema(RoleEntity entity) {
        return RoleSchema.builder()
                .id(entity.getId())
                .name(entity.getName())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deactivatedAt(entity.getDeactivatedAt())
                .build();
    }
}