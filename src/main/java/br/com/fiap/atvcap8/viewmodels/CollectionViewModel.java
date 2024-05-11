package br.com.fiap.atvcap8.viewmodels;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record CollectionViewModel(
        Long id,

        @NotNull
        @FutureOrPresent
        LocalDateTime dateTime,

        @NotNull
        @Positive
        Long container,

        @Positive
        @NotNull
        Long route
) {
}
