package com.proyectosemana1.creditosservice.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "credits")
public class Credit extends CreditProduct{

    private int numberOfInstallments;
    private float installmentAmount;
    private float interestRate;

}
