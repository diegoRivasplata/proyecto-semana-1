package com.proyectosemana1.creditosservice.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "payments")
public class Payment {

    @Id
    private String id;
    private float amount;
    private LocalDate paymentDate = LocalDate.now();
    private String typeOfProduct;
    private String creditProductId;

    public boolean validateAmount(){
        return (amount > 0);
    }
}
