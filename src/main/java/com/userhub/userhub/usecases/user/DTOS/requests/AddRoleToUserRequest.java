package com.userhub.userhub.usecases.user.DTOS.requests;

import java.util.UUID;

public class AddRoleToUserRequest {

    private UUID userId;
    private UUID roleId;

    public AddRoleToUserRequest(UUID userId, UUID roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getRoleId() {
        return roleId;
    }

}
