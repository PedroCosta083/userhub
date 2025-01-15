package com.userhub.userhub.adapters.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.userhub.userhub.adapters.mappers.UserMapper;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;
import com.userhub.userhub.infra.database.repositories.JpaUserRepository;
import com.userhub.userhub.infra.database.schemas.UserSchema;

@Repository
public class UserRepository implements UserRepositoryInterface {

    private final JpaUserRepository jpaUserRepository;

    public UserRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public void create(UserEntity user) {
        UserSchema userSchema = UserMapper.toSchema(user);
        jpaUserRepository.save(userSchema);
    }

    @Override
    public void updateUser(UserEntity user) {
        UserSchema userSchema = UserMapper.toSchema(user);
        jpaUserRepository.save(userSchema);
    }

    @Override
    public UserEntity searchByEmail(String email) {
        UserSchema userFinded = jpaUserRepository.findByEmail(email);
        return UserMapper.toDomain(userFinded);
    }

    @Override
    public UserEntity searchByLogin(String username) {
        UserSchema userFinded = jpaUserRepository.findByUsername(username);
        return UserMapper.toDomain(userFinded);
    }

    @Override
    public List<UserEntity> searchByRoleName(String roleName) {
        List<UserSchema> userSchemas = jpaUserRepository.findByRoles_name(roleName);
        return UserMapper.toDomainList(userSchemas);
    }

    @Override
    public UserEntity searchById(UUID id) {
        UserSchema userFinded = jpaUserRepository.findById(id).orElse(null);
        return UserMapper.toDomain(userFinded);
    }

    @Override
    public List<UserEntity> searchAll() {
        List<UserSchema> userSchemas = jpaUserRepository.findAll();
        return UserMapper.toDomainList(userSchemas);
    }

    @Override
    public void deleteAll() {
        jpaUserRepository.deleteAll();
    }

}
