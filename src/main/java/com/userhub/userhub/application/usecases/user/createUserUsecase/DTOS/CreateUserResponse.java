package com.userhub.userhub.application.usecases.user.createUserUsecase.DTOS;

import java.util.UUID;

import com.userhub.userhub.domain.objetcValues.Email;

public class CreateUserResponse {

    private UUID id;
    private String name;
    private Email email;
    private String username;
    private String message;

    public CreateUserResponse(UUID id, String name, Email email, String username, String message) {
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

    public Email getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

}
