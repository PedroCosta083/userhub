package com.userhub.userhub.application.usecases.user.createUserUsecase.DTOS;

import java.time.LocalDate;

public class CreateUserRequest {
    private String username;
    private String password;
    private String email;
    private LocalDate birthday;
    private String name;

    public CreateUserRequest(String name, String username, String email, String password, LocalDate birthday) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

}
