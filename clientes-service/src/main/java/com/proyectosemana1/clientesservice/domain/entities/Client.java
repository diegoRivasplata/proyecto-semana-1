package com.proyectosemana1.clientesservice.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    private String id;
    private String phoneNumber;
    private String city;
    private String address;
    private String email;
    private int creditScore = 0;
    private LocalDate creationDate = LocalDate.now();
    private boolean isActive = true;
}
