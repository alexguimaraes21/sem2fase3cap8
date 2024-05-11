package br.com.fiap.atvcap8.controllers;

import br.com.fiap.atvcap8.models.UserModel;
import br.com.fiap.atvcap8.responsemodels.TokenResponseModel;
import br.com.fiap.atvcap8.services.TokenService;
import br.com.fiap.atvcap8.services.UserService;
import br.com.fiap.atvcap8.viewmodels.LoginViewModel;
import br.com.fiap.atvcap8.viewmodels.UserViewModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginViewModel loginViewModel) {
        UsernamePasswordAuthenticationToken UsernamePassword =
                new UsernamePasswordAuthenticationToken(loginViewModel.email(), loginViewModel.password());
        Authentication auth = authenticationManager.authenticate(UsernamePassword);
        return new ResponseEntity<>(new TokenResponseModel(tokenService.tokenGenerator((UserModel) auth.getPrincipal())), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserViewModel userViewModel) {
        return new ResponseEntity<>(userService.add(userViewModel), HttpStatus.CREATED);
    }
}
