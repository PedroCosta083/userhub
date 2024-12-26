package com.userhub.userhub.domain.objetcValues;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.regex.Pattern;
import java.security.MessageDigest;

public class Password {
    private final String value;

    // private static final Pattern PASSWORD_PATTERN =
    // Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

    // public Password(String value) {
    // // if (value == null || !PASSWORD_PATTERN.matcher(value).matches()) {
    // // throw new IllegalArgumentException("Invalid password");
    // // }
    // if (value == null || value.length() < 8) {
    // throw new IllegalArgumentException("Invalid password");
    // }
    // this.value = hashPassword(value);
    // }

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

    public Password(String value) {
        if (value == null || !PASSWORD_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid password");
        }
        this.value = hashPassword(value);
    }

    // Construtor para senhas já hashadas
    public Password(String hashedValue, boolean isHashed) {
        if (!isHashed) {
            throw new IllegalArgumentException("Password must be hashed.");
        }
        this.value = hashedValue; // Recebe o hash diretamente
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public boolean verifyPassword(String password) {
        return this.value.equals(hashPassword(password));
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
        Password password = (Password) o;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Password{value=****}";
    }
}