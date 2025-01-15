package com.userhub.userhub.usecases.user.DTOS.requests;

public class FindByRoleNameRequest {
    private String roleName;

    public FindByRoleNameRequest(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
