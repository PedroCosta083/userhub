package com.userhub.userhub.usecases.user.DTOS.requests;

public class FindByUsernameRequest {

    private String username;

    public FindByUsernameRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
