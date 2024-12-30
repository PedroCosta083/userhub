package com.userhub.userhub.domain.objetcValues;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class UserName {
    private final String value;

    public UserName(String value, List<String> badWords) {
        validateUserName(value, badWords);
        this.value = value;
    }

    private void validateUserName(String value, List<String> badWords) {
        Objects.requireNonNull(value, "Username must not be null");

        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("Username must not be empty");
        }

        if (value.length() < 5 || value.length() > 20) {
            throw new IllegalArgumentException("Username must be between 5 and 20 characters");
        }

        if (!isValidUsernameFormat(value)) {
            throw new IllegalArgumentException("Username must be alphanumeric and cannot contain special characters");
        }
        if (badWords == null) {
            throw new IllegalArgumentException("Bad words list must not be null");
        }
        if (containsBadWord(value, badWords)) {
            throw new IllegalArgumentException("Username contains prohibited words");
        }
    }

    // Verifica se o username está no formato correto (alfanumérico)
    private boolean isValidUsernameFormat(String username) {
        String USERNAME_REGEX = "^[a-zA-Z0-9]+$"; // Somente letras e números
        return Pattern.matches(USERNAME_REGEX, username);
    }

    // Verifica se o username contém palavras proibidas
    private boolean containsBadWord(String username, List<String> badWords) {
        for (String badWord : badWords) {
            if (username.toLowerCase().contains(badWord.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserName userName = (UserName) o;
        return Objects.equals(value, userName.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "UserName{" +
                "value='" + value + '\'' +
                '}';
    }
}
