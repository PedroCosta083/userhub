package com.userhub.userhub.infra.repository.roles;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userhub.userhub.infra.schemas.role.RoleSchema;

import java.util.UUID;

public interface JpaRoleRepository extends JpaRepository<RoleSchema, UUID> {
    RoleSchema findByName(String name);
}
