package com.proyectosemana1.clientesservice.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "businessClients")
@TypeAlias("business")
public class BusinessClient extends Client {

    private String companyName;
    private String rucNumber;

    public BusinessClient(Client client){
        super.setAddress(client.getAddress());
        super.setCity(client.getAddress());
        super.setCreditScore(client.getCreditScore());
        super.setPhoneNumber(client.getPhoneNumber());
        super.setActive(client.isActive());
        super.setCreationDate(client.getCreationDate());
        super.setEmail(client.getEmail());
    }

    public boolean validateRucNumber() {
        if (this.rucNumber.length() == 10) return true;
        return false;
    }
}
