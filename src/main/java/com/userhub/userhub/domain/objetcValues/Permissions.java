package com.userhub.userhub.domain.objetcValues;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Permissions {

    private final Set<String> permissions;

    // Construtor que recebe um conjunto de permissões
    public Permissions(Set<String> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            throw new IllegalArgumentException("Permissions cannot be null or empty");
        }
        this.permissions = Collections.unmodifiableSet(new HashSet<>(permissions)); // Garante que a coleção seja
                                                                                    // imutável
    }

    // Método para verificar se uma permissão está presente
    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }

    // Getter para a coleção de permissões
    public Set<String> getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return "Permissions{" +
                "permissions=" + permissions +
                '}';
    }

    // Comparação de objetos com base nas permissões
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Permissions that = (Permissions) o;
        return permissions.equals(that.permissions);
    }

    @Override
    public int hashCode() {
        return permissions.hashCode();
    }
}