package br.com.fiap.atvcap8.viewmodels;

import br.com.fiap.atvcap8.models.ContainerModel;
import jakarta.validation.constraints.*;

public record ContainerViewModel(
        Long id,

        @NotBlank
        @Size(min = 1, max = 255)
        String location,

        @NotNull
        @PositiveOrZero
        Float capacity,

        @NotNull
        @Min(0)
        @Max(100)
        Integer currentLevel) {

}
