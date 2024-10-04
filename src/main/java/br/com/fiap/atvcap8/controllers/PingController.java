package br.com.fiap.atvcap8.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api")
public class PingController {

    @GetMapping("/v1/public/ping")
    public ResponseEntity<?> ping() {
        Map<String, LocalDateTime> ping = new HashMap<>();
        ping.put("ping", LocalDateTime.now());
        return new ResponseEntity<>(ping, HttpStatus.OK);
    }
}
