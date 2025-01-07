package com.userhub.userhub.application.usecases.user.createUserUsecase;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.userhub.userhub.application.usecases.user.createUserUsecase.DTOS.CreateUserRequest;
import com.userhub.userhub.application.usecases.user.createUserUsecase.DTOS.CreateUserResponse;

import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;
import com.userhub.userhub.domain.builders.RoleBuilder;
import com.userhub.userhub.domain.builders.UserBuilder;
import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;

import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.application.services.BadWordsLoaderService;
import com.userhub.userhub.domain.objetcValues.Login;
import com.userhub.userhub.domain.objetcValues.Password;
import com.userhub.userhub.domain.objetcValues.Email;
import com.userhub.userhub.domain.objetcValues.UserName;

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

            if (input.getRoles() != null && input.getRoles().length > 0) {
                Set<RoleEntity> roles = Set.of(input.getRoles()).stream()
                        .map(roleId -> new RoleBuilder().name(roleId).build())
                        .collect(Collectors.toSet());
                userEntity.addRoles(roles);
            }

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
