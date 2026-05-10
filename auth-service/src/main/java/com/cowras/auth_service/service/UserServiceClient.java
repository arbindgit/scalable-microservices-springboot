package com.cowras.auth_service.service;


import com.cowras.auth_service.model.UserDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserServiceClient {

    private final WebClient webClient;

    @Value("${user-service.base-url}")
    private String userServiceUrl;

    public UserDTO getUserByEmail(
            String email
    ) {

        return webClient

                .get()

                .uri(
                        userServiceUrl
                                + "/internal/users/"
                                + email
                )

                .retrieve()

                .bodyToMono(UserDTO.class)

                .block();
    }
}
