package com.userhub.userhub.usecases.user;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;
import com.userhub.userhub.usecases.user.DTOS.requests.EntityIdRequest;
import com.userhub.userhub.usecases.user.DTOS.responses.ActiveUserResponse;

public class ActiveUserUseCase implements BaseUseCaseInterface<EntityIdRequest, ActiveUserResponse> {
    private static final Logger LOGGER = Logger.getLogger(ActiveUserUseCase.class.getName());
    private final UserRepositoryInterface userRepository;

    public ActiveUserUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<ActiveUserResponse> execute(EntityIdRequest input) {
        validateInput(input);

        return CompletableFuture.supplyAsync(() -> {
            UserEntity user = findUserById(input.getId());
            validateUserStatus(user);

            activateUser(user);

            return buildResponse(user);
        });
    }

    private void validateInput(EntityIdRequest input) {
        if (input == null || input.getId() == null) {
            throw new IllegalArgumentException("Input or ID cannot be null.");
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

    private void validateUserStatus(UserEntity user) {
        if (user.isActive()) {
            LOGGER.warning("User already activated with ID: " + user.getId());
            throw new IllegalArgumentException("User is already activated.");
        }
    }

    private void activateUser(UserEntity user) {
        user.activate();
        userRepository.updateUser(user);
        LOGGER.info("User activated successfully with ID: " + user.getId());
    }

    private ActiveUserResponse buildResponse(UserEntity user) {
        return new ActiveUserResponse(
                user.getId(),
                user.getName(),
                user.isActive(),
                "User activated successfully.");
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}
