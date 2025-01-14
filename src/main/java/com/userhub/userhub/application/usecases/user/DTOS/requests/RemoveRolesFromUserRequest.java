package com.userhub.userhub.application.usecases.user.DTOS.requests;

import java.util.List;
import java.util.UUID;

public class RemoveRolesFromUserRequest {
    private UUID userId;
    private List<UUID> roleIds;

    public RemoveRolesFromUserRequest(UUID userId, List<UUID> roleIds) {
        this.userId = userId;
        this.roleIds = roleIds;
    }

    public UUID getUserId() {
        return userId;
    }

    public List<UUID> getRoleIds() {
        return roleIds;
    }

}
