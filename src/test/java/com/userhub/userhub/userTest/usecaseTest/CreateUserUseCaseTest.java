package com.userhub.userhub.userTest.usecaseTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.userhub.userhub.application.services.BadWordService;
import com.userhub.userhub.application.usecases.user.createUserUsecase.CreateUserUseCase;
import com.userhub.userhub.application.usecases.user.createUserUsecase.DTOS.CreateUserRequest;
import com.userhub.userhub.application.usecases.user.createUserUsecase.DTOS.CreateUserResponse;
import com.userhub.userhub.domain.entities.user.UserRepositoryInterface;
import com.userhub.userhub.domain.entities.user.UserEntity;

import java.io.IOException;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {

    @Mock
    private UserRepositoryInterface userRepository;

    private CreateUserUseCase createUserUseCase;

    @Mock
    private BadWordService badWordService;

    @BeforeEach
    public void setup() throws IOException {
        createUserUseCase = new CreateUserUseCase(userRepository);
    }

    @Test
    public void shouldCreateUserAndReturnResponse() {
        // Dados de entrada (input) para o teste
        CreateUserRequest request = new CreateUserRequest("Jhon Doe", "jhonny", "jhon@example.com", "jhonnX12345",
                LocalDate.of(1990, 1, 1), null);

        // Simulando o comportamento do repositório
        doNothing().when(userRepository).create(any(UserEntity.class)); // O método create não retorna nada

        // Executando o use case
        CompletableFuture<CreateUserResponse> responseFuture = createUserUseCase.execute(request);
        CreateUserResponse response = responseFuture.join(); // Aguarda a execução do CompletableFuture

        // Verificando se a resposta é válida
        assertNotNull(response);
        assertEquals("jhonny", response.getUsername());
        assertEquals("jhon@example.com", response.getEmail());
        assertEquals("User created successfully", response.getMessage());

        // Verificando se o repositório foi chamado corretamente
        verify(userRepository, times(1)).create(any(UserEntity.class));
    }

    @Test
    public void shouldThrowExceptionWhenUsernameContainsBadWord() {

        // Dados de entrada (input) para o teste
        CreateUserRequest request = new CreateUserRequest("Jhon Doe", "dicks", "jhon@example.com", "jhonnX12345",
                LocalDate.of(1990, 1, 1), null);

        // Executando o use case e capturando a exceção
        CompletionException exception = assertThrows(CompletionException.class, () -> {
            createUserUseCase.execute(request).join();
        });

        // Verificando a causa da exceção
        Throwable cause = exception.getCause();
        assertTrue(cause instanceof IllegalArgumentException);
        assertEquals("Username contains prohibited words", cause.getMessage());

        // Verificando se o repositório não foi chamado
        verify(userRepository, times(0)).create(any(UserEntity.class));
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsInvalid() {
        // Dados de entrada (input) para o teste
        CreateUserRequest request = new CreateUserRequest("Jhon Doe", "jhonny", "jhon@example.com", "123",
                LocalDate.of(1990, 1, 1), null);

        // Executando o use case e capturando a exceção
        CompletionException exception = assertThrows(CompletionException.class, () -> {
            createUserUseCase.execute(request).join();
        });

        // Verificando a causa da exceção
        Throwable cause = exception.getCause();
        assertTrue(cause instanceof IllegalArgumentException);
        assertEquals(
                "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, and one uppercase letter",
                cause.getMessage());

        // Verificando se o repositório não foi chamado
        verify(userRepository, times(0)).create(any(UserEntity.class));
    }

    @Test
    public void shouldThrowExceptionWhenBirthdayIsNull() {
        // Dados de entrada (input) para o teste
        CreateUserRequest request = new CreateUserRequest("Jhon Doe", "jhonny", "jhon@example.com", "jhonnX12345",
                null, null);

        // Executando o use case e capturando a exceção
        CompletionException exception = assertThrows(CompletionException.class, () -> {
            createUserUseCase.execute(request).join();
        });

        // Verificando a causa da exceção
        Throwable cause = exception.getCause();
        assertTrue(cause instanceof IllegalArgumentException);
        assertEquals("Birthday cannot be null", cause.getMessage());

        // Verificando se o repositório não foi chamado
        verify(userRepository, times(0)).create(any(UserEntity.class));
    }

}
