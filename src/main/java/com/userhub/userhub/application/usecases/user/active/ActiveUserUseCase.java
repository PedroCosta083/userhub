package com.userhub.userhub.application.usecases.user.active;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import com.userhub.userhub.application.usecases.user.DTOS.requests.EntityIdRequest;
import com.userhub.userhub.application.usecases.user.DTOS.responses.ActiveUserResponse;
import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;

public class ActiveUserUseCase implements BaseUseCaseInterface<EntityIdRequest, ActiveUserResponse> {
    private static final Logger LOGGER = Logger.getLogger(ActiveUserUseCase.class.getName());
    private final UserRepositoryInterface userRepository;

    public ActiveUserUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<ActiveUserResponse> execute(EntityIdRequest input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        return CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Searching for user with ID: " + input.getId());
            UserEntity user = userRepository.searchById(input.getId());
            if (user == null) {
                LOGGER.warning("User not found with ID: " + input.getId());
                throw new UserNotFoundException("User not found");
            }
            if (user.isActive()) {
                LOGGER.warning("User already activated with ID: " + input.getId());
                throw new IllegalArgumentException("User already activated");
            }
            user.activate();
            userRepository.updateUser(user);
            LOGGER.info("User activated successfully with ID: " + user.getId());
            return new ActiveUserResponse(
                    user.getId(),
                    user.getName(),
                    user.isActive(),
                    "User activated successfully");
        });
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}
