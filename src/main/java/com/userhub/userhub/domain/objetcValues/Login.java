package com.userhub.userhub.domain.objetcValues;

import java.util.Objects;
import java.util.regex.Pattern;

public class Login {

    private final String username;
    private final Password password;

    public Login(String username, Password password) {
        this.username = validateUsername(username);
        this.password = Objects.requireNonNull(password, "Password must not be null");
    }

    private String validateUsername(String username) {
        Objects.requireNonNull(username, "Username must not be null");

        if (username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username must not be empty");
        }
        System.out.println("username debug: " + username);
        System.out.println("username length: " + username.length());
        if (username.length() < 5 || username.length() > 20) {
            throw new IllegalArgumentException("Username must be between 5 and 20 characters");
        }

        if (!isValidUsernameFormat(username)) {
            throw new IllegalArgumentException("Username must be alphanumeric and cannot contain special characters");
        }

        return username;
    }

    // Verifica se o username está no formato correto (alfanumérico)
    private boolean isValidUsernameFormat(String username) {
        String USERNAME_REGEX = "^[a-zA-Z0-9]+$"; // Somente letras e números
        return Pattern.matches(USERNAME_REGEX, username);
    }

    public String getUsername() {
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