package com.userhub.userhub.usecases.user.DTOS.responses;

import java.util.List;

import com.userhub.userhub.usecases.user.DTOS.requests.FindAllUserRequest;

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
