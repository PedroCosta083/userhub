package com.userhub.userhub.infra.repository.roles;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.userhub.userhub.domain.entities.role.RoleEntity;

@Repository
public class RoleRepository implements RoleRepositoryInterface {


    private final JpaRoleRepository jpaRoleRepository;

    public RoleRepository(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    public void create(RoleEntity role) {
        jpaRoleRepository.save(role);
    }

    public void updateRole(RoleEntity role) {
        jpaRoleRepository.save(role);
    }

    public RoleEntity searchById(UUID id) {
        return jpaRoleRepository.findById(id).orElse(null);
    }

    public RoleEntity searchByName(String name) {
        return jpaRoleRepository.findByName(name);
    }

    public List<RoleEntity> searchAll() {
        return jpaRoleRepository.findAll();
    }

    @Override
    public void deleteAll() {
        jpaRoleRepository.deleteAll();
    }

}
