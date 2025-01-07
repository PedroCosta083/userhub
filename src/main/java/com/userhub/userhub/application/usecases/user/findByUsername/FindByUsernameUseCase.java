package com.userhub.userhub.application.usecases.user.findByUsername;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import com.userhub.userhub.application.usecases.user.DTOS.requests.FindByUsernameRequest;
import com.userhub.userhub.application.usecases.user.DTOS.responses.FindUserResponse;
import com.userhub.userhub.application.usecases.user.findAll.FindAllUserUseCase;
import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;

public class FindByUsernameUseCase implements BaseUseCaseInterface<FindByUsernameRequest, FindUserResponse> {

    private static final Logger LOGGER = Logger.getLogger(FindAllUserUseCase.class.getName());
    private final UserRepositoryInterface userRepository;

    public FindByUsernameUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<FindUserResponse> execute(FindByUsernameRequest input) {
        return CompletableFuture.supplyAsync(() -> {
            if (input.getUsername() == null) {
                throw new IllegalArgumentException("user name cannot be null");
            }
            UserEntity user = userRepository.searchByLogin(input.getUsername());
            if (user == null) {
                LOGGER.warning("User not found");
                throw new UserNotFoundException("Users not found");
            }
            return new FindUserResponse(
                    user.getId(),
                    user.getName(),
                    user.getLogin().getUsername().getValue(),
                    user.isActive(),
                    user.getEmail().getvalue(),
                    user.getBirthday(),
                    user.getUpdatedAt(),
                    "User found successfully");
        });
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

}
