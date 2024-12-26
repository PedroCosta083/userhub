package com.userhub.userhub.domain.objetcValues;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {
    private final String value;

    public Email(String value) {
        if (value == null || !isValidEmail(value)) {
            throw new IllegalArgumentException("Invalid email value");
        }
        this.value = value;
    }

    public String getvalue() {
        return value;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public boolean isValidEmail() {
        return isValidEmail(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Email email = (Email) o;
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Email{" +
                "value='" + value + '\'' +
                '}';
    }
}