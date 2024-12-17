package com.formbuilder.formbuilder.infra.repository.roles;

import com.formbuilder.formbuilder.domain.entities.userEntities.role.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, UUID> {
    RoleEntity findByName(String name);
}
