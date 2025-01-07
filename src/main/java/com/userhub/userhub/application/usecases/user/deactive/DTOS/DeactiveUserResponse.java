package com.userhub.userhub.application.usecases.user.deactive.DTOS;

import java.util.UUID;

public class DeactiveUserResponse {
    private final UUID userId;
    private final String userName;
    private final boolean isActive;
    private final String message;

    public DeactiveUserResponse(UUID userId, String userName, boolean isActive, String message) {
        this.userId = userId;
        this.userName = userName;
        this.isActive = isActive;
        this.message = message;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ActiveUserResponse{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", isActive=" + isActive +
                ", message='" + message +
                '}';
    }
}
