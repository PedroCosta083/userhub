package com.userhub.userhub.application.builders.base;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Builder<T extends Builder<T>> {
    protected UUID id = UUID.randomUUID(); // Valor padrão gerado automaticamente
    protected String name;
    protected Boolean active = true; // Exemplo de valor padrão
    protected LocalDate createdAt = LocalDate.now(); // Valor padrão
    protected LocalDate updatedAt = LocalDate.now(); // Valor padrão
    protected LocalDate deactivatedAt;

    @SuppressWarnings("unchecked")
    public T id(UUID id) {
        this.id = id;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T name(String name) {
        this.name = name;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T active(Boolean active) {
        this.active = active;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T createdAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T updatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T deactivatedAt(LocalDate deactivatedAt) {
        this.deactivatedAt = deactivatedAt;
        return (T) this;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return active;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public LocalDate getDeactivatedAt() {
        return deactivatedAt;
    }
}
