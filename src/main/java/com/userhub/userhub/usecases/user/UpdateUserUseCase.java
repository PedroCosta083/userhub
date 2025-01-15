package com.userhub.userhub.usecases.user;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import com.userhub.userhub.domain.builders.UserBuilder;
import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;
import com.userhub.userhub.domain.objetcValues.Email;
import com.userhub.userhub.usecases.user.DTOS.requests.UpdateUserRequest;
import com.userhub.userhub.usecases.user.DTOS.responses.UpdateUserResponse;

public class UpdateUserUseCase implements BaseUseCaseInterface<UpdateUserRequest, UpdateUserResponse> {

    private static final Logger LOGGER = Logger.getLogger(UpdateUserUseCase.class.getName());
    private final UserRepositoryInterface userRepository;

    public UpdateUserUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<UpdateUserResponse> execute(UpdateUserRequest input) {
        validateInput(input);

        return CompletableFuture.supplyAsync(() -> {
            UserEntity existingUser = findUserById(input.getId());

            UserEntity updatedUser = buildUpdatedUser(existingUser, input);
            userRepository.updateUser(updatedUser);

            LOGGER.info("User updated successfully with ID: " + updatedUser.getId());

            return buildResponse(updatedUser);
        });
    }

    private void validateInput(UpdateUserRequest input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }
        if (input.getId() == null) {
            throw new IllegalArgumentException("User ID is required.");
        }
        if (input.getName() == null || input.getName().isBlank()) {
            throw new IllegalArgumentException("User name is required.");
        }
        if (input.getEmail() == null || input.getEmail().isBlank()) {
            throw new IllegalArgumentException("User email is required.");
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

    private UserEntity buildUpdatedUser(UserEntity existingUser, UpdateUserRequest input) {
        LOGGER.info("Building updated user entity for ID: " + existingUser.getId());
        return new UserBuilder()
                .id(existingUser.getId())
                .name(input.getName())
                .active(existingUser.isActive())
                .createdAt(existingUser.getCreatedAt())
                .deactivatedAt(existingUser.getDeactivatedAt())
                .birthday(input.getBirthday())
                .login(existingUser.getLogin())
                .email(new Email(input.getEmail()))
                .roles(existingUser.getRoles())
                .build();
    }

    private UpdateUserResponse buildResponse(UserEntity updatedUser) {
        return new UpdateUserResponse(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getEmail().getvalue(),
                updatedUser.getLogin().getUsername().getValue(),
                "User updated successfully"
        );
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}
