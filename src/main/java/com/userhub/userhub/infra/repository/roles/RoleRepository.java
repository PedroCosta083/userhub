package com.userhub.userhub.infra.repository.roles;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.domain.entities.role.RoleRepositoryInterface;
import com.userhub.userhub.infra.schemas.role.RoleMapper;
import com.userhub.userhub.infra.schemas.role.RoleSchema;

@Repository
public class RoleRepository implements RoleRepositoryInterface {

    private final JpaRoleRepository jpaRoleRepository;

    public RoleRepository(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    public void create(RoleEntity role) {
        RoleSchema roleSchema = RoleMapper.toSchema(role);
        jpaRoleRepository.save(roleSchema);
    }

    public void updateRole(RoleEntity role) {
        RoleSchema roleSchema = RoleMapper.toSchema(role);
        jpaRoleRepository.save(roleSchema);
    }

    public RoleEntity searchById(UUID id) {
        RoleSchema roleSchema = jpaRoleRepository.findById(id).orElse(null);
        return RoleMapper.toDomain(roleSchema);
    }

    public Set<RoleEntity> searchByIds(List<UUID> ids) {
        List<RoleSchema> roleSchemas = jpaRoleRepository.findAllById(ids);
        return RoleMapper.toDomainSet(roleSchemas);
    }

    public RoleEntity searchByName(String name) {
        RoleSchema roleSchema = jpaRoleRepository.findByName(name);
        return RoleMapper.toDomain(roleSchema);
    }

    public List<RoleEntity> searchAll() {
        List<RoleSchema> roleSchemas = jpaRoleRepository.findAll();
        return RoleMapper.toDomainList(roleSchemas);
    }

    @Override
    public void deleteAll() {
        jpaRoleRepository.deleteAll();
    }

}
