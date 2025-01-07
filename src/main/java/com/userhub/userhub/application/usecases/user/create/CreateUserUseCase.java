package com.userhub.userhub.application.usecases.user.create;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;
import com.userhub.userhub.application.usecases.user.DTOS.requests.CreateUserRequest;
import com.userhub.userhub.application.usecases.user.DTOS.responses.CreateUserResponse;
import com.userhub.userhub.domain.builders.UserBuilder;
import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;

import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.objetcValues.Login;
import com.userhub.userhub.domain.objetcValues.Password;
import com.userhub.userhub.domain.objetcValues.Email;
import com.userhub.userhub.domain.objetcValues.UserName;
import com.userhub.userhub.infra.services.BadWordsLoaderService;

public class CreateUserUseCase implements BaseUseCaseInterface<CreateUserRequest, CreateUserResponse> {

    private final UserRepositoryInterface userRepository;
    private final BadWordsLoaderService badWordService;

    public CreateUserUseCase(UserRepositoryInterface userRepository, BadWordsLoaderService badWordService) {
        this.userRepository = userRepository;
        this.badWordService = badWordService;
    }

    @Override
    public CompletableFuture<CreateUserResponse> execute(CreateUserRequest input) {
        return CompletableFuture.supplyAsync(() -> {
            List<String> badWords = null;
            try {
                badWords = badWordService.loadBadWords(input.getBadWordsFilePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            validateRequest(input);
            UserEntity userEntity = new UserBuilder()
                    .name(input.getName())
                    .email(new Email(input.getEmail()))
                    .login(new Login(
                            new UserName(input.getUsername(), badWords),
                            new Password(input.getPassword())))
                    .birthday(input.getBirthday())
                    .build();
            userRepository.create(userEntity);

            return new CreateUserResponse(
                    userEntity.getId(),
                    userEntity.getName(),
                    userEntity.getEmail().getvalue(),
                    userEntity.getLogin().getUsername().getValue(),
                    "User created successfully");
        });

    }

    private void validateRequest(CreateUserRequest input) {

        if (input.getName() == null || input.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (input.getEmail() == null || input.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (input.getUsername() == null || input.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (input.getPassword() == null || input.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (input.getBadWordsFilePath() == null || input.getBadWordsFilePath().isBlank()) {
            throw new IllegalArgumentException("Bad words file path is required");

        }
    }

}
