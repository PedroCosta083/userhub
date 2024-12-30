package com.userhub.userhub.application.usecases.user.createUserUsecase;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.userhub.userhub.application.usecases.user.createUserUsecase.DTOS.CreateUserRequest;
import com.userhub.userhub.application.usecases.user.createUserUsecase.DTOS.CreateUserResponse;

import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;
import com.userhub.userhub.domain.builders.role.RoleBuilder;
import com.userhub.userhub.domain.builders.user.UserBuilder;
import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;

import com.userhub.userhub.domain.entities.role.RoleEntity;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.application.services.BadWordService;
import com.userhub.userhub.domain.objetcValues.Login;
import com.userhub.userhub.domain.objetcValues.Password;
import com.userhub.userhub.domain.objetcValues.Email;
import com.userhub.userhub.domain.objetcValues.UserName;

import java.io.IOException;

public class CreateUserUseCase implements BaseUseCaseInterface<CreateUserRequest, CreateUserResponse> {

    private final UserRepositoryInterface userRepository;

    private final BadWordService badWordService;

    public CreateUserUseCase(UserRepositoryInterface userRepository) throws IOException {
        this.userRepository = userRepository;
        this.badWordService = new BadWordService();
    }

    // public CreateUserUseCase(UserRepositoryInterface userRepository) {
    // this.userRepository = userRepository;
    // }

    @Override
    public CompletableFuture<CreateUserResponse> execute(CreateUserRequest input) {
        return CompletableFuture.supplyAsync(() -> {
            UserEntity userEntity = new UserBuilder()
                    .name(input.getName())
                    .email(new Email(input.getEmail()))
                    .login(new Login(
                            new UserName(input.getUsername(), badWordService.getBadWords()),
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

}
