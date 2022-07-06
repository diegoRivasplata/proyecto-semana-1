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
@Document("personalAccounts")
public class PersonalAccount {

    @Id
    private String id;
    private String accountType;
    private String accountNumber;
    private String currency;
    private float availableBalance = 0;
    private LocalDateTime openingDate = LocalDateTime.now();
    private boolean commissionFree;
    private int monthlyMovementsAllowed;
    private int numberOfMovementsMade = 0;
    private String clientId;
}
