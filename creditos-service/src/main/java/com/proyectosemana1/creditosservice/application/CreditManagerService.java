package com.proyectosemana1.creditosservice.application;

import com.proyectosemana1.creditosservice.domain.entities.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditManagerService {
    Flux<Credit> findAll();
    Mono<Credit> save(Credit credit);
    Mono<Credit> findById(String id);
    Mono<Credit> update(String id, Credit credit);
    Mono<Void> delete(String id);
}
