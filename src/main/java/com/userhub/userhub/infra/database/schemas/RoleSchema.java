package com.userhub.userhub.infra.database.schemas;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "roles")
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@Getter
public class RoleSchema extends BaseSchema {

}
