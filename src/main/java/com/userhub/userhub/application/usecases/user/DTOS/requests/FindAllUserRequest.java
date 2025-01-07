package com.userhub.userhub.application.usecases.user.DTOS.requests;

import java.time.LocalDate;
import java.util.UUID;

public class FindAllUserRequest {

    private UUID id;
    private String name;
    private String username;
    private boolean active;
    private String email;
    private LocalDate birthday;
    private LocalDate updatedAt;

    public FindAllUserRequest(UUID id, String name, String username, boolean active, String email, LocalDate birthday,
            LocalDate updatedAt) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.active = active;
        this.email = email;
        this.birthday = birthday;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

}
