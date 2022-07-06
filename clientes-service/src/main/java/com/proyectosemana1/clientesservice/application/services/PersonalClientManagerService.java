package com.proyectosemana1.clientesservice.application.services;

import com.proyectosemana1.clientesservice.domain.entities.PersonalClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonalClientManagerService {
    Flux<PersonalClient> findAll();
    Mono<PersonalClient> save(PersonalClient client);
    Mono<PersonalClient> findById(String id);
    Mono<PersonalClient> update(String id, PersonalClient personalClient);
    Mono<Void> delete(String id);
}
