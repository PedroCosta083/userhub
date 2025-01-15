package com.userhub.userhub.usecases.user;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;
import com.userhub.userhub.usecases.user.DTOS.requests.EntityIdRequest;
import com.userhub.userhub.usecases.user.DTOS.responses.DeactiveUserResponse;

public class DeactiveUserUseCase implements BaseUseCaseInterface<EntityIdRequest, DeactiveUserResponse> {
    private static final Logger LOGGER = Logger.getLogger(DeactiveUserUseCase.class.getName());
    private final UserRepositoryInterface userRepository;

    public DeactiveUserUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<DeactiveUserResponse> execute(EntityIdRequest input) {
        validateInput(input);

        return CompletableFuture.supplyAsync(() -> {
            UserEntity user = findUserById(input.getId());
            validateUserStatus(user);

            deactivateUser(user);

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
        if (!user.isActive()) {
            LOGGER.warning("User already deactivated with ID: " + user.getId());
            throw new IllegalArgumentException("User is already deactivated.");
        }
    }

    private void deactivateUser(UserEntity user) {
        user.deactivate();
        userRepository.updateUser(user);
        LOGGER.info("User deactivated successfully with ID: " + user.getId());
    }

    private DeactiveUserResponse buildResponse(UserEntity user) {
        return new DeactiveUserResponse(
                user.getId(),
                user.getName(),
                user.isActive(),
                "User deactivated successfully.");
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}
