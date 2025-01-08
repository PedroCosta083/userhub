package com.userhub.userhub.application.usecases.user.addRole;

import java.util.concurrent.CompletableFuture;

import com.userhub.userhub.application.usecases.user.DTOS.requests.AddRoleToUserRequest;
import com.userhub.userhub.application.usecases.user.DTOS.responses.AddRoleToUserResponse;
import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;
import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.domain.entities.role.RoleRepositoryInterface;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;

public class AddRoleToUserUseCase implements BaseUseCaseInterface<AddRoleToUserRequest, AddRoleToUserResponse> {

    private final UserRepositoryInterface userRepository;
    private final RoleRepositoryInterface roleRepository;

    public AddRoleToUserUseCase(UserRepositoryInterface userRepository, RoleRepositoryInterface roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public CompletableFuture<AddRoleToUserResponse> execute(AddRoleToUserRequest input) {

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
            return new AddRoleToUserResponse(
                    user.getId(),
                    user.getLogin().getUsername().getValue(),
                    user.getName(),
                    role.getId(),
                    role.getName(),
                    "Role added successfully");
        });
    }

}
