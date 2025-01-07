package com.userhub.userhub.application.usecases.user.deactive.DTOS;

import java.util.UUID;

public class DeactiveUserRequest {
    private final UUID id;

    public DeactiveUserRequest(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
