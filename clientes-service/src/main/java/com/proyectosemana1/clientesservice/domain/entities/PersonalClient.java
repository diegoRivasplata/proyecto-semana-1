package com.proyectosemana1.clientesservice.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "personalClients")
@TypeAlias("personal")
public class PersonalClient extends Client {

    private String name;
    private String lastName;
    private String dni;

    public boolean validateDni() {
        if (this.dni.length() == 8) return true;
        return false;
    }
}
