package com.userhub.userhub.usecases.user.DTOS.responses;

import java.util.UUID;

public class RemoveRoleUserResponse {
    private UUID userId;
    private String userLogin;
    private String userName;
    private UUID roleId;
    private String roleName;
    private String message;

    public RemoveRoleUserResponse(UUID userId, String userLogin, String userName, UUID roleId, String roleName,
            String message) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.userName = userName;
        this.roleId = roleId;
        this.roleName = roleName;
        this.message = message;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserName() {
        return userName;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getMessage() {
        return message;
    }
}
