package br.com.fiap.atvcap8.viewmodels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UserUpdatePasswordViewModel(
        @Positive
        @NotNull
        Long id,

        @NotBlank
        @Size(min = 6, max = 20)
        String oldPassword,

        @NotBlank
        @Size(min = 6, max = 20)
        String newPassword
) {
}
