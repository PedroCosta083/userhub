package com.userhub.userhub.usecases.user;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;
import com.userhub.userhub.domain.builders.UserBuilder;
import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.objetcValues.Login;
import com.userhub.userhub.domain.objetcValues.Password;
import com.userhub.userhub.domain.objetcValues.Email;
import com.userhub.userhub.domain.objetcValues.UserName;
import com.userhub.userhub.infra.loaders.BadWordsFileLoader;
import com.userhub.userhub.usecases.user.DTOS.requests.CreateUserRequest;
import com.userhub.userhub.usecases.user.DTOS.responses.CreateUserResponse;

public class CreateUserUseCase implements BaseUseCaseInterface<CreateUserRequest, CreateUserResponse> {

    private final UserRepositoryInterface userRepository;
    private final BadWordsFileLoader badWordsLoader;

    public CreateUserUseCase(UserRepositoryInterface userRepository, BadWordsFileLoader badWordsLoader) {
        this.userRepository = userRepository;
        this.badWordsLoader = badWordsLoader;
    }

    @Override
    public CompletableFuture<CreateUserResponse> execute(CreateUserRequest input) {
        return CompletableFuture.supplyAsync(() -> {
            validateRequest(input);

            List<String> badWords = loadBadWords(input.getBadWordsFilePath());

            UserEntity userEntity = buildUserEntity(input, badWords);
            userRepository.create(userEntity);

            return buildResponse(userEntity);
        });
    }

    private void validateRequest(CreateUserRequest input) {
        if (input.getName() == null || input.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required and cannot be blank.");
        }
        if (input.getEmail() == null || input.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required and cannot be blank.");
        }
        if (input.getUsername() == null || input.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username is required and cannot be blank.");
        }
        if (input.getPassword() == null || input.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required and cannot be blank.");
        }
        if (input.getBadWordsFilePath() == null || input.getBadWordsFilePath().isBlank()) {
            throw new IllegalArgumentException("Bad words file path is required and cannot be blank.");
        }
    }

    private List<String> loadBadWords(String filePath) {
        try {
            return badWordsLoader.loadBadWords(filePath);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load bad words from file: " + filePath, e);
        }
    }

    private UserEntity buildUserEntity(CreateUserRequest input, List<String> badWords) {
        return new UserBuilder()
                .name(input.getName())
                .email(new Email(input.getEmail()))
                .login(new Login(
                        new UserName(input.getUsername(), badWords),
                        new Password(input.getPassword())))
                .birthday(input.getBirthday())
                .build();
    }

    private CreateUserResponse buildResponse(UserEntity userEntity) {
        return new CreateUserResponse(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail().getvalue(),
                userEntity.getLogin().getUsername().getValue(),
                "User created successfully.");
    }
}
