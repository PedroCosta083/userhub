package com.userhub.userhub.application.usecases.user.findAll;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.userhub.userhub.application.usecases.user.findAll.DTOS.FindAllUserRequest;
import com.userhub.userhub.application.usecases.user.findAll.DTOS.FindAllUsersResponse;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;

import java.util.List;

public class FindAllUserUseCase implements FindAllUsersUseCaseInterface<FindAllUsersResponse> {
    private static final Logger LOGGER = Logger.getLogger(FindAllUserUseCase.class.getName());
    private final UserRepositoryInterface userRepository;

    public FindAllUserUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<FindAllUsersResponse> execute() {
        return CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Searching for all users");
            List<UserEntity> users = userRepository.searchAll();
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
