package com.userhub.userhub.usecases.user.findAll;

import java.util.concurrent.CompletableFuture;

import com.userhub.userhub.usecases.user.DTOS.responses.FindAllUsersResponse;

public interface FindAllUsersUseCaseInterface<O> {
    CompletableFuture<FindAllUsersResponse> execute();
}
