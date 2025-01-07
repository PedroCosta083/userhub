package com.userhub.userhub.application.usecases.user.findAll;

import java.util.concurrent.CompletableFuture;

import com.userhub.userhub.application.usecases.user.DTOS.responses.FindAllUsersResponse;

public interface FindAllUsersUseCaseInterface<O> {
    CompletableFuture<FindAllUsersResponse> execute();
}
