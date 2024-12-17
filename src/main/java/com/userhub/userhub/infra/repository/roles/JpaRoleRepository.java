package com.userhub.userhub.infra.repository.roles;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userhub.userhub.domain.entities.role.RoleEntity;

import java.util.UUID;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, UUID> {
    RoleEntity findByName(String name);
}
