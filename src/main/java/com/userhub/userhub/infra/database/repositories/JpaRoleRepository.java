package com.userhub.userhub.infra.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userhub.userhub.infra.database.schemas.RoleSchema;

import java.util.UUID;

public interface JpaRoleRepository extends JpaRepository<RoleSchema, UUID> {
    RoleSchema findByName(String name);
}
