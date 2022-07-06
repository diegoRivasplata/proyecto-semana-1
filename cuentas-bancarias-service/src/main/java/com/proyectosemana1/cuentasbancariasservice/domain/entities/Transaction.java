package com.proyectosemana1.cuentasbancariasservice.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("transactions")
public class Transaction {

    @Id
    private String id;
    private String transactionType;
    private float amount;
    private LocalDateTime transactionDate = LocalDateTime.now();
    private String accountId;
}
