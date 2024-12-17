package com.formbuilder.formbuilder.domain.entities.userEntities.user;

import java.time.LocalDate;
import java.util.Set;

import com.formbuilder.formbuilder.domain.entities.base.BaseInterface;
import com.formbuilder.formbuilder.domain.entities.userEntities.role.RoleEntity;

public interface UserInterface extends BaseInterface {

    LocalDate getBirthday();

    String getLogin();

    String getEmail();

    String getPassword();

    Set<RoleEntity> getRoles();

}
