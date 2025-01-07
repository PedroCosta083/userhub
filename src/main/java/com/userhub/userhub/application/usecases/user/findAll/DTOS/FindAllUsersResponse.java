package com.userhub.userhub.application.usecases.user.findAll.DTOS;

import java.util.List;

public class FindAllUsersResponse {
    private List<FindAllUserRequest> users;
    private String message;

    public FindAllUsersResponse(List<FindAllUserRequest> users, String message) {
        this.users = users;
        this.message = message;
    }

    public List<FindAllUserRequest> getUsers() {
        return users;
    }

    public String getMessage() {
        return message;
    }

}
