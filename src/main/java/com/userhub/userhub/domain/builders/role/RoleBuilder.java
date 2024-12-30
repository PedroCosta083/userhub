package com.userhub.userhub.domain.builders.role;

import com.userhub.userhub.domain.builders.base.Builder;
import com.userhub.userhub.domain.entities.role.RoleEntity;

public class RoleBuilder extends Builder<RoleBuilder> {
    public RoleEntity build() {
        return new RoleEntity(this);
    }
}
