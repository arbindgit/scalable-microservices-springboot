package com.cowras.auth_service.service;

import com.cowras.auth_service.model.LoginRequest;
import com.cowras.auth_service.model.LoginResponse;
import com.cowras.auth_service.util.JwtUtil;
import com.cowras.auth_service.model.UserResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    /**
     * Login Method
     */
    public LoginResponse login(LoginRequest request) {

        // =========================
        // CALL USER SERVICE
        // =========================

        String url =
                "http://localhost:8081/api/users/email/"
                        + request.getEmail();

        UserResponse user =
                restTemplate.getForObject(url, UserResponse.class);

        if (user == null) {
            throw new RuntimeException("Invalid Email or Password");
        }

        boolean passwordMatched =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!passwordMatched) {
            throw new RuntimeException("Invalid Email or Password");
        }

        String token =
                jwtUtil.generateToken(user.getEmail(),user.getRole());

        // =========================
        // RETURN RESPONSE
        // =========================

        return new LoginResponse(token,"Bearer",86400L);
    }
}