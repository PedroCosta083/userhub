package com.userhub.userhub.application.usecases.user.addRoles;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.userhub.userhub.application.usecases.user.DTOS.requests.AddRolesToUserRequest;
import com.userhub.userhub.application.usecases.user.DTOS.responses.AddRolesToUserResponse;
import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;
import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.domain.entities.role.RoleRepositoryInterface;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;

public class AddRolesToUserUseCase implements BaseUseCaseInterface<AddRolesToUserRequest, AddRolesToUserResponse> {
    private final UserRepositoryInterface userRepository;
    private final RoleRepositoryInterface roleRepository;

    public AddRolesToUserUseCase(UserRepositoryInterface userRepository, RoleRepositoryInterface roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public CompletableFuture<AddRolesToUserResponse> execute(AddRolesToUserRequest input) {

        return CompletableFuture.supplyAsync(() -> {
            if (input == null) {
                throw new IllegalArgumentException("Input cannot be null");
            }
            if (input.getUserId() == null || input.getRoleIds() == null) {
                throw new IllegalArgumentException("User ID and Role ID cannot be null");
            }
            UserEntity user = userRepository.searchById(input.getUserId());
            Set<RoleEntity> roles = roleRepository.searchByIds(input.getRoleIds());
            if (roles.isEmpty()) {
                throw new IllegalArgumentException("No roles found for the provided IDs.");
            }

            if (user == null) {
                throw new IllegalArgumentException("User not found with ID: " + input.getUserId());
            }

            user.addRoles(roles);
            userRepository.updateUser(user);

            List<String> roleNames = roles.stream()
                    .map(RoleEntity::getName)
                    .collect(Collectors.toList());

            return new AddRolesToUserResponse(
                    user.getId(),
                    user.getLogin().getUsername().getValue(),
                    user.getName(),
                    roleNames,
                    "Roles added successfully");
        });
    }
}
