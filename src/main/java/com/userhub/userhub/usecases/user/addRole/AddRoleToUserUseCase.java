package com.userhub.userhub.usecases.user.addRole;

import java.util.concurrent.CompletableFuture;

import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;
import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.domain.entities.role.RoleRepositoryInterface;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;
import com.userhub.userhub.usecases.user.DTOS.requests.AddRoleToUserRequest;
import com.userhub.userhub.usecases.user.DTOS.responses.AddRoleToUserResponse;

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
                throw new IllegalArgumentException("User ID and Role ID cannot be null");
            }

            UserEntity user = userRepository.searchById(input.getUserId());
            RoleEntity role = roleRepository.searchById(input.getRoleId());

            if (user == null) {
                throw new IllegalArgumentException("User not found with ID: " + input.getUserId());
            }
            if (role == null) {
                throw new IllegalArgumentException("Role not found with ID: " + input.getRoleId());
            }

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
