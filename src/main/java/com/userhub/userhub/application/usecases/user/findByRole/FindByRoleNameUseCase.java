package com.userhub.userhub.application.usecases.user.findByRole;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.List;
import java.util.logging.Logger;

import com.userhub.userhub.application.usecases.user.DTOS.requests.FindAllUserRequest;
import com.userhub.userhub.application.usecases.user.DTOS.requests.FindByRoleNameRequest;
import com.userhub.userhub.application.usecases.user.DTOS.responses.FindAllUsersResponse;
import com.userhub.userhub.application.usecases.user.findAll.FindAllUserUseCase;
import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;

import com.userhub.userhub.domain.entities.user.UserEntity;

public class FindByRoleNameUseCase implements BaseUseCaseInterface<FindByRoleNameRequest, FindAllUsersResponse> {
    private static final Logger LOGGER = Logger.getLogger(FindAllUserUseCase.class.getName());
    private final UserRepositoryInterface userRepository;

    public FindByRoleNameUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<FindAllUsersResponse> execute(FindByRoleNameRequest input) {
        return CompletableFuture.supplyAsync(() -> {
            if (input.getRoleName() == null) {
                throw new IllegalArgumentException("Role name cannot be null");
            }
            List<UserEntity> users = userRepository.searchByRoleName(input.getRoleName());
            if (users == null) {
                LOGGER.warning("Users not found");
                throw new UserNotFoundException("Users not found");
            }
            List<FindAllUserRequest> usersData = users.stream().map(user -> new FindAllUserRequest(
                    user.getId(),
                    user.getName(),
                    user.getLogin().getUsername().getValue(),
                    user.isActive(),
                    user.getEmail().getvalue(),
                    user.getBirthday(),
                    user.getUpdatedAt())).collect(Collectors.toList());
            LOGGER.info("Users found successfully");
            return new FindAllUsersResponse(usersData, "Users found successfully");
        });
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

}
