package com.userhub.userhub.domain.entities.user;

import java.time.LocalDate;
import java.util.Set;

import com.userhub.userhub.domain.entities.base.BaseInterface;
import com.userhub.userhub.domain.entities.role.RoleEntity;

import com.userhub.userhub.domain.objetcValues.Login;

public interface UserInterface extends BaseInterface {

    LocalDate getBirthday();

    Login getLogin();

    String getEmail();

    String getPassword();

    Set<RoleEntity> getRoles();

}
