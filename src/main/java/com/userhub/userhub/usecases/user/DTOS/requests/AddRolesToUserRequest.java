package com.userhub.userhub.usecases.user.DTOS.requests;

import java.util.List;
import java.util.UUID;

public class AddRolesToUserRequest {
    private UUID userId;
    private List<UUID> roleIds;

    public AddRolesToUserRequest(UUID userId, List<UUID> roleIds) {
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
