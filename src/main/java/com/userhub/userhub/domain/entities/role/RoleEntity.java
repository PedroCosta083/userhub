package com.userhub.userhub.domain.entities.role;

import java.time.LocalDate;

import com.userhub.userhub.domain.entities.base.BaseEntity;

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
