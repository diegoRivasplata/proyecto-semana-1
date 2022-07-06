package com.proyectosemana1.creditosservice.application;

import com.proyectosemana1.creditosservice.domain.entities.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentManagerService {
    Flux<Payment> findAll();
    Mono<Payment> save(Payment payment);
    Mono<Payment> findById(String id);
    Mono<Void> delete(String id);
}
