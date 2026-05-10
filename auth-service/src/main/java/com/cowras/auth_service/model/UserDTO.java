package com.cowras.auth_service.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private String email;
    private String password;
    private List<String> roles;
}
