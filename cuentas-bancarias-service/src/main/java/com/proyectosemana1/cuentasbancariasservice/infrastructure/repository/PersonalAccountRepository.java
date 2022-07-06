package com.proyectosemana1.cuentasbancariasservice.infrastructure.repository;

import com.proyectosemana1.cuentasbancariasservice.domain.entities.PersonalAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PersonalAccountRepository extends ReactiveMongoRepository<PersonalAccount, String> {
    Flux<PersonalAccount> findAllByClientId(String clientId);
}
