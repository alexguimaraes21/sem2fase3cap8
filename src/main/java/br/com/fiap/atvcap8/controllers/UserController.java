package br.com.fiap.atvcap8.controllers;

import br.com.fiap.atvcap8.responsemodels.UserResponseModel;
import br.com.fiap.atvcap8.services.UserService;
import br.com.fiap.atvcap8.viewmodels.UserUpdatePasswordViewModel;
import br.com.fiap.atvcap8.viewmodels.UserViewModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/v1/user")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserResponseModel> findAll(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @PostMapping("/v1/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseModel add(@RequestBody @Valid UserViewModel viewModel) {
        return userService.add(viewModel);
    }

    @GetMapping(value = "/v1/user", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseModel findById(@RequestParam Long id) {
        return userService.findById(id);
    }

    @GetMapping(value = "/v1/user", params = "email")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseModel findByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @PutMapping("/v1/user")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseModel update(@RequestBody UserViewModel viewModel) {
        return userService.update(viewModel);
    }

    @PatchMapping("/v1/user")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseModel changePassword(@RequestBody UserUpdatePasswordViewModel viewModel) {
        return userService.updatePassword(viewModel);
    }

    @DeleteMapping(value = "/v1/user", params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam Long id) {
        userService.delete(id);
    }
}
