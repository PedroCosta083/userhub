package com.userhub.userhub.usecases.user.DTOS.requests;

import java.time.LocalDate;
import java.util.UUID;

public class UpdateUserRequest {
    private UUID id;
    private String username;
    private String email;
    private LocalDate birthday;
    private String name;
    private String message;

    public UpdateUserRequest(UUID id, String name, String username, String email, LocalDate birthday, String message) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.birthday = birthday;
        this.name = name;
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

}
