package com.proyectosemana1.creditosservice.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditProduct {

    @Id
    private String id;
    private String currency;
    private float creditAmount;
    private String clientId;
    private float totalDebt = 0;

}
