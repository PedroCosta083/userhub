package com.formbuilder.formbuilder.infra.repository.users;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

import com.formbuilder.formbuilder.domain.entities.userEntities.user.UserEntity;

public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {
    // O Spring Data já fornece os métodos básicos (save, delete, findById, etc.)
    UserEntity findByLogin(String login);

    UserEntity findByEmail(String email);

    // @Query("SELECT u FROM UserEntity u JOIN u.roles r WHERE r.name = :roleName")
    List<UserEntity> findByRoles_name(String roleName); // Nova consulta para buscar usuários por
                                                        // role

}
