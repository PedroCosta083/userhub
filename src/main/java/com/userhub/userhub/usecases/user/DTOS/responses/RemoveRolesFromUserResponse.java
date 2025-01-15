package com.userhub.userhub.usecases.user.DTOS.responses;

import java.util.List;
import java.util.UUID;

public class RemoveRolesFromUserResponse {
    private UUID userId;
    private String userLogin;
    private String userName;
    private List<String> roles;
    private String message;

    public RemoveRolesFromUserResponse(UUID userId, String userLogin, String userName, List<String> roles,
            String message) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.userName = userName;
        this.roles = roles;
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

    public List<String> getRoles() {
        return roles;
    }

    public String getMessage() {
        return message;
    }
}
