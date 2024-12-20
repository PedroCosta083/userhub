package com.userhub.userhub.domain.entities.role;

import java.util.List;
import java.util.UUID;

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
