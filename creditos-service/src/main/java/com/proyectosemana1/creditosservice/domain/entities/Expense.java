package com.proyectosemana1.creditosservice.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "expenses")
public class Expense {

    @Id
    private String id;
    private float amount;
    private String currency;
    private int numberOfInstallments;
    private LocalDate expenseDate = LocalDate.now();
    private String creditCardId;

    public boolean validateAmount(){
        return (this.amount > 0);
    }

    public boolean validateNumberOfInstallments(){
        return (this.numberOfInstallments > 0);
    }

    public boolean validateCurrency(){
        return (this.currency.equalsIgnoreCase("PEN") || this.currency.equalsIgnoreCase(("USD")));
    }

}
