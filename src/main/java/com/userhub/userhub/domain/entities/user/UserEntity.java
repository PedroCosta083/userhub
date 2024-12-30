package com.userhub.userhub.domain.entities.user;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.userhub.userhub.domain.builders.user.UserBuilder;
import com.userhub.userhub.domain.entities.base.BaseEntity;
import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.domain.objetcValues.Login;
import com.userhub.userhub.domain.objetcValues.Email;

public class UserEntity extends BaseEntity implements UserInterface {

    private LocalDate birthday;

    private Login login;

    private Email email;

    private Set<RoleEntity> roles;

    public UserEntity(UserBuilder builder) {
        super(builder);
        this.birthday = builder.getBirthday();
        this.login = builder.getLogin();
        this.email = builder.getEmail();
        this.roles = builder.getRoles();
        this.validate();
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Login getLogin() {
        return login;
    }

    public Email getEmail() {
        return email;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    // Métodos auxiliares
    public void addRole(RoleEntity role) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.add(role);
    }

    public void removeRole(RoleEntity role) {
        if (this.roles != null) {
            this.roles.remove(role);
        }
    }

    public void addRoles(Set<RoleEntity> roles) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.addAll(roles);
    }

    public void validate() {
        super.validate();
        if (birthday == null)
            throw new IllegalArgumentException("Birthday cannot be null");
    }

    @Override
    public String toString() {
        return "UserEntitie {" + '\n' +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", birthday=" + birthday +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", active=" + isActive() +
                ", createdAT=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                ", deactivatedAt=" + getDeactivatedAt() +
                ", roles=" + roles +
                '}';
    }
}
