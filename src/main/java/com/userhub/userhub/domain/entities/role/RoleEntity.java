package com.userhub.userhub.domain.entities.role;

import com.userhub.userhub.domain.entities.base.BaseEntity;
import com.userhub.userhub.application.builders.role.RoleBuilder;

// import java.util.UUID;
// import java.time.LocalDate;

public class RoleEntity extends BaseEntity implements RoleInterface {


    public RoleEntity(RoleBuilder builder) {
        super(builder);
    }

    @Override
    public String toString() {
        return "RoleEntity{id=" + getId() + ", roleName='" + getName() + "'}";
    }
}
