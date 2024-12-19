package com.userhub.userhub.domain.entities.base;

import java.time.LocalDate;
import java.util.UUID;

public class BaseEntity implements BaseInterface {

    private UUID id;

    private String name;

    private Boolean active;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private LocalDate deactivatedAt;

    public BaseEntity(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.active = true;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.deactivatedAt = null;
    }

    public BaseEntity(UUID id, String name, Boolean active, LocalDate createdAT, LocalDate updatedAt,
            LocalDate deactivatedAt) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.createdAt = createdAT != null ? createdAT : LocalDate.now();
        this.updatedAt = updatedAt != null ? updatedAt : LocalDate.now();
        this.deactivatedAt = deactivatedAt != null ? deactivatedAt : null;
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
        return createdAt;
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
        if (this.id == null) {
            throw new IllegalArgumentException("ID is required");
        }
        if (this.name == null || this.name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (this.active == null) {
            throw new IllegalArgumentException("Active status is required");
        }
        if (this.createdAt == null) {
            throw new IllegalArgumentException("Created date is required");
        }
        if (this.updatedAt == null) {
            throw new IllegalArgumentException("Updated date is required");
        }
        if (this.deactivatedAt != null && this.active) {
            throw new IllegalArgumentException("Deactivated date should be null if the entity is active");
        }
    }
}
