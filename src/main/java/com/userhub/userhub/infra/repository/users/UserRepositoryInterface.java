package com.userhub.userhub.infra.repository.users;

import java.util.List;
import java.util.UUID;

import com.userhub.userhub.domain.entities.user.UserEntity;

public interface UserRepositoryInterface {

    void create(UserEntity user);

    void updateUser(UserEntity user);

    void deleteAll();

    UserEntity searchById(UUID id);
    
    UserEntity searchByEmail(String email);
    
    UserEntity searchByLogin(String login);
    
    List<UserEntity> searchByRole(String roleName);

    List<UserEntity> searchAll();
    
    
    // void active(String id);

    // void deactivate(UserEntity user);
}