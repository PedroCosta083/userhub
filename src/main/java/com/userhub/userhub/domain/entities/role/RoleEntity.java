package com.userhub.userhub.domain.entities.role;

import com.userhub.userhub.domain.entities.base.BaseEntity;

import java.util.UUID;
import java.time.LocalDate;

public class RoleEntity extends BaseEntity implements RoleInterface {

    public RoleEntity(String name) {
        super(name);
    }

    public RoleEntity(UUID id, String name, Boolean active, LocalDate createdAt, LocalDate updatedAt,
            LocalDate deactivatedAt) {
        super(id, name, active, createdAt, updatedAt, deactivatedAt);
    }

    @Override
    public String toString() {
        return "RoleEntity{id=" + getId() + ", roleName='" + getName() + "'}";
    }
}
