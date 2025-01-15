package com.userhub.userhub.usecases.user;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.domain.entities.role.RoleRepositoryInterface;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;
import com.userhub.userhub.usecases.user.DTOS.requests.RemoveRolesFromUserRequest;
import com.userhub.userhub.usecases.user.DTOS.responses.RemoveRolesFromUserResponse;

public class RemoveRolesFromUserUseCase {

    private static final Logger LOGGER = Logger.getLogger(RemoveRolesFromUserUseCase.class.getName());
    private final UserRepositoryInterface userRepository;
    private final RoleRepositoryInterface roleRepository;

    public RemoveRolesFromUserUseCase(UserRepositoryInterface userRepository, RoleRepositoryInterface roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public CompletableFuture<RemoveRolesFromUserResponse> execute(RemoveRolesFromUserRequest input) {
        validateInput(input);

        return CompletableFuture.supplyAsync(() -> {
            UserEntity user = findUserById(input.getUserId());
            Set<RoleEntity> rolesToRemove = findRolesByIds(input.getRoleIds());

            removeRolesFromUser(user, rolesToRemove);
            updateUser(user);

            return buildResponse(user, rolesToRemove);
        });
    }

    private void validateInput(RemoveRolesFromUserRequest input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }
        if (input.getUserId() == null) {
            throw new IllegalArgumentException("User ID is required.");
        }
        if (input.getRoleIds() == null || input.getRoleIds().isEmpty()) {
            throw new IllegalArgumentException("At least one Role ID is required.");
        }
    }

    private UserEntity findUserById(UUID userId) {
        LOGGER.info("Searching for user with ID: " + userId);
        UserEntity user = userRepository.searchById(userId);
        if (user == null) {
            LOGGER.warning("User not found with ID: " + userId);
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
        return user;
    }

    private Set<RoleEntity> findRolesByIds(List<UUID> roleIds) {
        LOGGER.info("Searching for roles with IDs: " + roleIds);
        Set<RoleEntity> roles = roleRepository.searchByIds(roleIds);
        if (roles.isEmpty()) {
            LOGGER.warning("No roles found for the provided IDs: " + roleIds);
            throw new RolesNotFoundException("No roles found for the provided IDs.");
        }
        return roles;
    }

    private void removeRolesFromUser(UserEntity user, Set<RoleEntity> roles) {
        LOGGER.info("Removing roles from user with ID: " + user.getId());
        user.removeRoles(roles);
    }

    private void updateUser(UserEntity user) {
        LOGGER.info("Updating user with ID: " + user.getId());
        userRepository.updateUser(user);
    }

    private RemoveRolesFromUserResponse buildResponse(UserEntity user, Set<RoleEntity> removedRoles) {
        List<String> roleNames = removedRoles.stream()
                .map(RoleEntity::getName)
                .collect(Collectors.toList());

        return new RemoveRolesFromUserResponse(
                user.getId(),
                user.getLogin().getUsername().getValue(),
                user.getName(),
                roleNames,
                "Roles removed successfully");
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class RolesNotFoundException extends RuntimeException {
        public RolesNotFoundException(String message) {
            super(message);
        }
    }
}
