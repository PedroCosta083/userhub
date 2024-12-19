package com.userhub.userhub.infra.schemas.role;

import java.util.List;
import java.util.stream.Collectors;

import com.userhub.userhub.domain.entities.role.RoleEntity;

public class RoleMapper {
    public static RoleEntity toDomain(RoleSchema schema) {
        return new RoleEntity(
                schema.getId(),
                schema.getName(),
                schema.getActive(),
                schema.getCreatedAt(),
                schema.getUpdatedAt(),
                schema.getDeactivatedAt());
    }

    public static List<RoleEntity> toDomainList(List<RoleSchema> schemas) {
        return schemas.stream()
                .map(RoleMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static RoleSchema toSchema(RoleEntity entity) {
        return RoleSchema.builder()
                .id(entity.getId())
                .name(entity.getName())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAT())
                .updatedAt(entity.getUpdatedAt())
                .deactivatedAt(entity.getDeactivatedAt())
                .build();
    }
}