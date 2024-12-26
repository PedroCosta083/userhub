package com.userhub.userhub.application.builders.user;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.application.builders.base.Builder;

import com.userhub.userhub.domain.objetcValues.Login;
import com.userhub.userhub.domain.objetcValues.Email;

public class UserBuilder extends Builder<UserBuilder> {

    private LocalDate birthday;
    private Login login;
    private Email email;
    private Set<RoleEntity> roles = new HashSet<>();

    public UserBuilder birthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public UserBuilder login(Login login) {
        this.login = login;
        return this;
    }

    public UserBuilder email(Email email) {
        this.email = email;
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

    public Login getLogin() {
        return login;
    }

    public Email getEmail() {
        return email;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }
}
