package com.formbuilder.formbuilder.infra.repository.users;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.formbuilder.formbuilder.domain.entities.userEntities.user.UserEntity;

@Repository
public class UserRepository implements UserRepositoryInterface {

    private final JpaUserRepository jpaUserRepository;

    public UserRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public void create(UserEntity user) {
        jpaUserRepository.save(user);
    }

    @Override
    public void updateUser(UserEntity user) {
        jpaUserRepository.save(user);
    }

    @Override
    public UserEntity searchByEmail(String email) {
        return jpaUserRepository.findByEmail(email);
    }

    @Override
    public UserEntity searchByLogin(String login) {
        return jpaUserRepository.findByLogin(login);
    }

    @Override
    public List<UserEntity> searchByRole(String roleName) {
        return jpaUserRepository.findByRoles_name(roleName);
    }

    @Override
    public UserEntity searchById(UUID id) {
        return jpaUserRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserEntity> searchAll() {
        return jpaUserRepository.findAll();
    }

    @Override
    public void deleteAll() {
        jpaUserRepository.deleteAll();
    }
    

}
