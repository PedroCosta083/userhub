package com.userhub.userhub.usecases.user.findById;

import java.util.concurrent.CompletableFuture;

import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;
import com.userhub.userhub.usecases.user.DTOS.requests.EntityIdRequest;
import com.userhub.userhub.usecases.user.DTOS.responses.FindUserResponse;

public class FindByIdUseCase implements BaseUseCaseInterface<EntityIdRequest, FindUserResponse> {

    private final UserRepositoryInterface userRepository;

    public FindByIdUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<FindUserResponse> execute(EntityIdRequest input) {
        return CompletableFuture.supplyAsync(() -> {
            if (input.getId() == null) {
                throw new IllegalArgumentException("Id cannot be null");
            }
            UserEntity user = userRepository.searchById(input.getId());
            if (user == null) {
                throw new IllegalArgumentException("User not found");
            }
            return new FindUserResponse(
                    user.getId(),
                    user.getName(),
                    user.getLogin().getUsername().getValue(),
                    user.isActive(),
                    user.getEmail().getvalue(),
                    user.getBirthday(),
                    user.getUpdatedAt(),
                    "User found");
        });
    }

}
