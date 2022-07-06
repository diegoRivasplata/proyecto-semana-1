package com.proyectosemana1.creditosservice.application;

import com.proyectosemana1.creditosservice.domain.entities.CreditCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditCardManagerService {
    Flux<CreditCard> findAll();
    Mono<CreditCard> save(CreditCard creditCard);
    Mono<CreditCard> findById(String id);
    Mono<CreditCard> update(String id, CreditCard creditCard);
    Mono<Void> delete(String id);

}
