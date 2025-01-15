package com.userhub.userhub.usecases.user.DTOS.responses;

import java.util.UUID;

public class ActiveUserResponse {
    private final UUID userId;
    private final String userName;
    private final boolean isActive;
    private final String message;

    public ActiveUserResponse(UUID userId, String userName, boolean isActive, String message) {
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
