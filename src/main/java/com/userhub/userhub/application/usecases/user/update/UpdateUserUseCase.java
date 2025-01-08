package com.userhub.userhub.application.usecases.user.update;

import java.util.concurrent.CompletableFuture;

import com.userhub.userhub.application.usecases.user.DTOS.requests.UpdateUserRequest;
import com.userhub.userhub.application.usecases.user.DTOS.responses.UpdateUserResponse;
import com.userhub.userhub.domain.builders.UserBuilder;
import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;
import com.userhub.userhub.domain.objetcValues.Email;

public class UpdateUserUseCase implements BaseUseCaseInterface<UpdateUserRequest, UpdateUserResponse> {

    private final UserRepositoryInterface userRepository;

    public UpdateUserUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<UpdateUserResponse> execute(UpdateUserRequest input) {
        return CompletableFuture.supplyAsync(() -> {

            UserEntity user = userRepository.searchById(input.getId());

            if (user == null) {
                throw new IllegalArgumentException("User not found");
            }
            UserEntity userUpdated = new UserBuilder()
                    .id(user.getId())
                    .name(input.getName())
                    .active(user.isActive())
                    .createdAt(user.getCreatedAt())
                    .deactivatedAt(user.getDeactivatedAt())
                    .birthday(input.getBirthday())
                    .login(user.getLogin())
                    .email(new Email(input.getEmail()))
                    .roles(user.getRoles())
                    .build();
            userRepository.updateUser(userUpdated);

            return new UpdateUserResponse(
                    userUpdated.getId(),
                    userUpdated.getName(),
                    userUpdated.getEmail().getvalue(),
                    userUpdated.getLogin().getUsername().getValue(),
                    "User updated successfully");
        });

    }
}
