package com.userhub.userhub.application.usecases.user.findAll;

import java.util.concurrent.CompletableFuture;

import com.userhub.userhub.application.usecases.user.findAll.DTOS.FindAllUsersResponse;

public interface FindAllUsersUseCaseInterface<O> {
    CompletableFuture<FindAllUsersResponse> execute();
}
