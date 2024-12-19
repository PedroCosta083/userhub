package com.userhub.userhub.infra.schemas.user;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
// import java.util.Set;
import java.util.List;

import com.userhub.userhub.infra.schemas.BaseSchema;
import com.userhub.userhub.infra.schemas.role.RoleSchema;

@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserSchema extends BaseSchema {

    private LocalDate birthday;

    private String login;

    private String email;

    private String password;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleSchema> roles;
}
