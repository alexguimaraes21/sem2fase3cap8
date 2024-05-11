package br.com.fiap.atvcap8.viewmodels;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginViewModel(
        Long id,

        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 6, max = 20)
        String password) {
}
