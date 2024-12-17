package com.userhub.userhub.domain.entities.user;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import com.userhub.userhub.domain.entities.base.BaseEntity;
import com.userhub.userhub.domain.entities.role.RoleEntity;

@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserEntity extends BaseEntity implements UserInterface {

    private LocalDate birthday;

    private String login;

    private String email;

    private String password;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    public UserEntity(
            // UUID id,
            String name,
            LocalDate birthday,
            String login,
            String email,
            String password,
            Set<RoleEntity> roles,
            boolean active,
            LocalDate createdAT,
            LocalDate updatedAt,
            LocalDate deactivatedAt) {
        super(name, active, createdAT, updatedAt, deactivatedAt);
        this.birthday = birthday;
        this.login = login;
        this.email = email;
        this.password = password;
        this.roles = (roles != null) ? roles : Set.of();
        validate();
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    // Métodos auxiliares
    public void addRole(RoleEntity role) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.add(role);
    }

    public void removeRole(RoleEntity role) {
        if (this.roles != null) {
            this.roles.remove(role);
        }
    }

    public void validate() {
        super.validate();
        if (birthday == null)
            throw new IllegalArgumentException("Birthday cannot be null");
        if (login == null || login.isEmpty())
            throw new IllegalArgumentException("Login cannot be null or empty");
        if (email == null || email.isEmpty() || !isValidEmail(email))
            throw new IllegalArgumentException("Invalid email");
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Password cannot be null or empty");
    }

    // Método auxiliar para validar o formato do e-mail
    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    @Override
    public String toString() {
        return "UserEntitie {" + '\n' +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", birthday=" + birthday +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + isActive() +
                ", createdAT=" + getCreatedAT() +
                ", updatedAt=" + getUpdatedAt() +
                ", deactivatedAt=" + getDeactivatedAt() +
                ", roles=" + roles +
                '}';
    }
}
