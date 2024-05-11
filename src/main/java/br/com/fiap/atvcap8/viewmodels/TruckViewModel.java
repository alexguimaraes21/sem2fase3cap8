package br.com.fiap.atvcap8.viewmodels;

import br.com.fiap.atvcap8.models.RouteModel;
import br.com.fiap.atvcap8.models.TruckModel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record TruckViewModel(
        Long id,

        @NotBlank
        @Size(min = 7, max = 7)
        String licensePlate,

        @NotNull
        @Min(value = 1)
        Float capacity,

        Boolean available) {
}
