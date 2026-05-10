package com.cowras.auth_service.controller;


import com.cowras.auth_service.model.LoginRequest;
import com.cowras.auth_service.model.LoginResponse;
import com.cowras.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    /**
     * Login API
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Health Check API
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {

        return ResponseEntity.ok("Auth Service Running...");
    }
}