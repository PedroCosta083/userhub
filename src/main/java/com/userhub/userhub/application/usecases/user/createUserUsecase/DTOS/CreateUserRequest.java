package com.userhub.userhub.application.usecases.user.createUserUsecase.DTOS;

import java.time.LocalDate;

public class CreateUserRequest {
    private String username;
    private String password;
    private String email;
    private LocalDate birthday;
    private String name;
    private String[] roles;
    private String badWordsFilePath;

    public CreateUserRequest(String name, String username, String email, String password, LocalDate birthday,
            String[] roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.name = name;
        this.roles = roles;
        this.badWordsFilePath = "src/main/resources/config/badwords.json";
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

    public String[] getRoles() {
        return roles;
    }

    public String getBadWordsFilePath() {
        return badWordsFilePath;
    }

    

}
