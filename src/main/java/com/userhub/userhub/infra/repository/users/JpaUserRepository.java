package com.userhub.userhub.infra.repository.users;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

import com.userhub.userhub.infra.schemas.user.UserSchema;

public interface JpaUserRepository extends JpaRepository<UserSchema, UUID> {
    // O Spring Data já fornece os métodos básicos (save, delete, findById, etc.)
    UserSchema findByUsername(String username);

    UserSchema findByEmail(String email);

    // @Query("SELECT u FROM UserSchema u JOIN u.roles r WHERE r.name = :roleName")
    List<UserSchema> findByRoles_name(String roleName); // Nova consulta para buscar usuários por
                                                        // role

}
