package com.userhub.userhub.infra.database.schemas;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
// @NoArgsConstructor
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BaseSchema {

    @Id
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String name;

    private Boolean active;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private LocalDate deactivatedAt;
}
