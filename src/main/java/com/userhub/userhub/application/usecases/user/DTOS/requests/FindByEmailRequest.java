package com.userhub.userhub.application.usecases.user.DTOS.requests;

public class FindByEmailRequest {
    private String email;

    public FindByEmailRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
