package com.proyectosemana1.cuentasbancariasservice.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("businessAccounts")
public class BusinessAccount extends PersonalAccount {

    private List<String> accountHolders;
    private List<String> authorizedSigners;

}
