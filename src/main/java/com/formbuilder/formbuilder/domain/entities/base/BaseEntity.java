package com.formbuilder.formbuilder.domain.entities.base;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
public class BaseEntity implements BaseInterface {
    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String name;

    private boolean active;

    private LocalDate createdAT;

    private LocalDate updatedAt;

    private LocalDate deactivatedAt;

    public BaseEntity(
            String name,
            boolean active,
            LocalDate createdAT,
            LocalDate updatedAt,
            LocalDate deactivatedAt) {
        this.name = name;
        this.active = true;
        this.createdAT = (createdAT != null) ? createdAT : LocalDate.now();
        this.updatedAt = (updatedAt != null) ? updatedAt : LocalDate.now();
        this.deactivatedAt = deactivatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDate getCreatedAT() {
        return createdAT;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public LocalDate getDeactivatedAt() {
        return deactivatedAt;
    }

    public void activate() {
        this.active = true;
        this.updatedAt = LocalDate.now();
    }

    public void deactivate() {
        this.active = false;
        this.updatedAt = LocalDate.now();
        this.deactivatedAt = LocalDate.now();
    }

    public void validate() {
        if (this.name == null || this.name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (!Boolean.class.isInstance(this.active)) {
            throw new IllegalArgumentException("Active must be a boolean value.");
        }
    }

}
