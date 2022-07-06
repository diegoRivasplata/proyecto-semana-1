package com.proyectosemana1.creditosservice.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "creditCards")
public class CreditCard extends CreditProduct{

    private String creditCardNumber;
    private boolean isAbroadShoppingAllowed = false;
    private boolean isOverdraftAllowed = false;
    private float overdraftFee = 10;
    private float availableBalance = super.getCreditAmount();

}
