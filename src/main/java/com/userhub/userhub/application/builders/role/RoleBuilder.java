package com.userhub.userhub.application.builders.role;

import com.userhub.userhub.application.builders.base.Builder;
import com.userhub.userhub.domain.entities.role.RoleEntity;

public class RoleBuilder extends Builder<RoleBuilder> {
    public RoleEntity build() {
        return new RoleEntity(this);
    }
}
