package com.cowras.farmer_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "farmers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phone_number;
}