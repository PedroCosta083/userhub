package com.userhub.userhub.usecases.user.DTOS.requests;

import java.util.UUID;

public class EntityIdRequest {
    private final UUID id;

    public EntityIdRequest(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
