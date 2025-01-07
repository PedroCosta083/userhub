package com.userhub.userhub.application.usecases.user.active.DTOS;

import java.util.UUID;

public class ActiveUserRequest {
    private final UUID id;

    public ActiveUserRequest(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
