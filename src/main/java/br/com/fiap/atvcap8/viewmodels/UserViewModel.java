package br.com.fiap.atvcap8.viewmodels;

import br.com.fiap.atvcap8.enums.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserViewModel(
        Long id,

        @NotBlank
        @Size(min = 3, max = 255)
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 6, max = 20)
        String password,

        UserRoleEnum role,
        Boolean accountNonExpired,
        Boolean credentialsNonExpired,
        Boolean accountNonLocked,
        Boolean enabled) {

}
