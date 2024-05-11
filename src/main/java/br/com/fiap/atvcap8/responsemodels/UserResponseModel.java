package br.com.fiap.atvcap8.responsemodels;

import br.com.fiap.atvcap8.enums.UserRoleEnum;
import br.com.fiap.atvcap8.models.UserModel;
import jakarta.persistence.Column;

public record UserResponseModel(Long id,
                                String name,
                                String email,
                                UserRoleEnum role,
                                Boolean accountNonExpired,
                                Boolean credentialsNonExpired,
                                Boolean accountNonLocked,
                                Boolean enabled) {
    public UserResponseModel(UserModel user) {
        this(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),
                user.getAccountNonLocked(),
                user.getEnabled());
    }
}
