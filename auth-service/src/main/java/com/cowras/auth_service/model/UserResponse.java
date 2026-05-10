package com.cowras.auth_service.model;
import lombok.Builder;
import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String role;
    private String phoneNumber;
    private String password;
}