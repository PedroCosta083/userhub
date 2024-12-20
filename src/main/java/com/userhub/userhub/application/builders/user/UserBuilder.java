package com.userhub.userhub.application.builders.user;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.application.builders.base.Builder;

public class UserBuilder extends Builder<UserBuilder> {

    private LocalDate birthday;
    private String login;
    private String email;
    private String password;
    private Set<RoleEntity> roles = new HashSet<>();

    public UserBuilder birthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public UserBuilder login(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder roles(Set<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public UserEntity build() {
        return new UserEntity(this);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }
}
