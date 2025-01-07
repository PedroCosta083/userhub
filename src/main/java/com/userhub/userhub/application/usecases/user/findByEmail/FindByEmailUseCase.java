package com.userhub.userhub.application.usecases.user.findByEmail;

import java.util.concurrent.CompletableFuture;

import com.userhub.userhub.application.usecases.user.DTOS.requests.FindByEmailRequest;
import com.userhub.userhub.application.usecases.user.DTOS.responses.FindUserResponse;
import com.userhub.userhub.domain.entities.base.BaseUseCaseInterface;
import com.userhub.userhub.domain.entities.user.UserEntity;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;

public class FindByEmailUseCase implements BaseUseCaseInterface<FindByEmailRequest, FindUserResponse> {

    private UserRepositoryInterface userRepository;

    public FindByEmailUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<FindUserResponse> execute(FindByEmailRequest input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        return CompletableFuture.supplyAsync(() -> {
            UserEntity user = userRepository.searchByEmail(input.getEmail());
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
