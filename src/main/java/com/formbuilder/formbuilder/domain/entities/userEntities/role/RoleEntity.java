package com.formbuilder.formbuilder.domain.entities.userEntities.role;

import java.time.LocalDate;
import java.util.UUID;

import com.formbuilder.formbuilder.domain.entities.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "roles")
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends BaseEntity implements RoleInterface {

    public RoleEntity(String name, boolean active, LocalDate createdAT, LocalDate updatedAt, LocalDate deactivatedAt) {
        super(name, active, createdAT, updatedAt, deactivatedAt);
    }

    @Override
    public String toString() {
        return "RoleEntity{id=" + getId() + ", roleName='" + getName() + "'}";
    }
}
