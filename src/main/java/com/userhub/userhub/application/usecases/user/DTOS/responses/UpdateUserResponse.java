package com.userhub.userhub.application.usecases.user.DTOS.responses;

import java.util.UUID;

public class UpdateUserResponse {

    private UUID id;
    private String name;
    private String email;
    private String username;
    private String message;

    public UpdateUserResponse(UUID id, String name, String email, String username, String message) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

}
