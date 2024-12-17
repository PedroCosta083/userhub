package com.userhub.userhub.infra.repository.roles;

import java.util.List;
import java.util.UUID;

import com.userhub.userhub.domain.entities.role.RoleEntity;

public interface RoleRepositoryInterface {
    void create(RoleEntity role);

    void updateRole(RoleEntity role);

    void deleteAll();

    RoleEntity searchById(UUID id);

    RoleEntity searchByName(String name);

    List<RoleEntity> searchAll();
    // void active(String id);
    // void deactivate(UserEntity user);
}
