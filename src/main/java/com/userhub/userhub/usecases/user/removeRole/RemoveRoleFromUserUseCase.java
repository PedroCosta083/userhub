package com.userhub.userhub.usecases.user.removeRole;

import java.util.concurrent.CompletableFuture;

import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;
import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.domain.entities.role.RoleRepositoryInterface;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;
import com.userhub.userhub.usecases.user.DTOS.requests.RemoveRoleUserRequest;
import com.userhub.userhub.usecases.user.DTOS.responses.RemoveRoleUserResponse;

public class RemoveRoleFromUserUseCase implements BaseUseCaseInterface<RemoveRoleUserRequest, RemoveRoleUserResponse> {

    private final UserRepositoryInterface userRepository;
    private final RoleRepositoryInterface roleRepository;

    public RemoveRoleFromUserUseCase(UserRepositoryInterface userRepository, RoleRepositoryInterface roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public CompletableFuture<RemoveRoleUserResponse> execute(RemoveRoleUserRequest input) {

        return CompletableFuture.supplyAsync(() -> {
            if (input == null) {
                throw new IllegalArgumentException("Input cannot be null");
            }
            if (input.getUserId() == null || input.getRoleId() == null) {
                throw new IllegalArgumentException("User ID and Role name cannot be null");
            }
            if (userRepository.searchById(input.getUserId()) == null) {
                throw new IllegalArgumentException("User not found with ID: " + input.getUserId());
            }
            if (roleRepository.searchById(input.getRoleId()) == null) {
                throw new IllegalArgumentException("Role not found with ID: " + input.getRoleId());
            }

            UserEntity user = userRepository.searchById(input.getUserId());
            RoleEntity role = roleRepository.searchById(input.getRoleId());

            user.addRole(role);
            userRepository.updateUser(user);
            return new RemoveRoleUserResponse(
                    user.getId(),
                    user.getLogin().getUsername().getValue(),
                    user.getName(),
                    role.getId(),
                    role.getName(),
                    "Role added successfully");
        });
    }

}
