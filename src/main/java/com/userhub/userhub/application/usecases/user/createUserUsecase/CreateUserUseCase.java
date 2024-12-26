package com.userhub.userhub.application.usecases.user.createUserUsecase;

import java.util.concurrent.CompletableFuture;

import com.userhub.userhub.application.usecases.user.createUserUsecase.DTOS.CreateUserRequest;
import com.userhub.userhub.application.usecases.user.createUserUsecase.DTOS.CreateUserResponse;

import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;
import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;

import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.application.builders.user.UserBuilder;

import com.userhub.userhub.domain.objetcValues.Login;
import com.userhub.userhub.domain.objetcValues.Password;
import com.userhub.userhub.domain.objetcValues.Email;

public class CreateUserUseCase implements BaseUseCaseInterface<CreateUserRequest, CreateUserResponse> {

    private final UserRepositoryInterface userRepository;

    public CreateUserUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<CreateUserResponse> execute(CreateUserRequest input) {
        return CompletableFuture.supplyAsync(() -> {

            UserEntity userEntity = new UserBuilder()
                    .name(input.getName())
                    .email(new Email(input.getEmail()))
                    .login(new Login(input.getUsername(), new Password(input.getPassword())))
                    .birthday(input.getBirthday())
                    // .roles(input.getRoles())
                    .build();

            userRepository.create(userEntity);

            return new CreateUserResponse(userEntity.getId(), userEntity.getName(), userEntity.getEmail(),
                    userEntity.getLogin().getUsername(),
                    "User created successfully");
        });

    }

}
