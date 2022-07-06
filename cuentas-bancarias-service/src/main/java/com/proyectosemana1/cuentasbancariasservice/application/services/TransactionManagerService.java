package com.proyectosemana1.cuentasbancariasservice.application.services;

import com.proyectosemana1.cuentasbancariasservice.domain.entities.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionManagerService {
    Flux<Transaction> findAll();
    Mono<Transaction> findById(String id);
    Mono<Transaction> sava(Transaction transaction);
    Mono<Transaction> update(String id, Transaction transaction);
    Mono<Void> delete(String id);
}
