package br.com.fiap.atvcap8.viewmodels;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record RouteViewModel(
        Long id,

        @NotBlank
        @Size(min = 1, max = 2147483647)
        String description,

        @NotNull
        @Future
        LocalDateTime startTime,

        @FutureOrPresent
        LocalDateTime endTime,

        @NotNull
        @Positive
        Long truck) {
}
