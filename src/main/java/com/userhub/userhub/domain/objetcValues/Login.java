package com.userhub.userhub.domain.objetcValues;

import java.util.Objects;

public class Login {

    private final UserName username;
    private final Password password;

    public Login(UserName username, Password password) {
        this.username = username;
        this.password = password;
    }

    public UserName getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Login login = (Login) o;
        return username.equals(login.username) && password.equals(login.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password=" + password +
                '}';
    }

}