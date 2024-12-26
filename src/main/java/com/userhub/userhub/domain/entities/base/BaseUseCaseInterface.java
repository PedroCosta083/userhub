package com.userhub.userhub.domain.entities.base;

import java.util.concurrent.CompletableFuture;

public interface BaseUseCaseInterface<I, O> {
    CompletableFuture<O> execute(I input);
}