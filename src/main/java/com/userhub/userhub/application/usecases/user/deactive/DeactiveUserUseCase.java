package com.userhub.userhub.application.usecases.user.deactive;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import com.userhub.userhub.application.usecases.user.DTOS.requests.EntityIdRequest;
import com.userhub.userhub.application.usecases.user.DTOS.responses.DeactiveUserResponse;
import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;

public class DeactiveUserUseCase implements BaseUseCaseInterface<EntityIdRequest, DeactiveUserResponse> {
    private static final Logger LOGGER = Logger.getLogger(DeactiveUserUseCase.class.getName());
    private final UserRepositoryInterface userRepository;

    public DeactiveUserUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<DeactiveUserResponse> execute(EntityIdRequest input) {
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
            if (user.isActive() == false) {
                LOGGER.warning("User already deactived with ID: " + input.getId());
                throw new IllegalArgumentException("User already deactived");
            }
            user.deactivate();
            userRepository.updateUser(user);
            LOGGER.info("User deactived successfully with ID: " + user.getId());
            return new DeactiveUserResponse(
                    user.getId(),
                    user.getName(),
                    user.isActive(),
                    "User deactived successfully");
        });
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}
