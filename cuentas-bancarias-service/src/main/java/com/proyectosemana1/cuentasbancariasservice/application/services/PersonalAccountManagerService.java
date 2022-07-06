package com.proyectosemana1.cuentasbancariasservice.application.services;

import com.proyectosemana1.cuentasbancariasservice.domain.entities.PersonalAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonalAccountManagerService {
    Flux<PersonalAccount> findAll();
    Mono<PersonalAccount> findById(String id);
    Mono<PersonalAccount> save(PersonalAccount personalAccount);
    Mono<PersonalAccount> update(String id, PersonalAccount personalAccount);
    Mono<Void> delete(String id);
}
