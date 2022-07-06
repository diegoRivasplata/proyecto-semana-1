package com.proyectosemana1.creditosservice.application;

import com.proyectosemana1.creditosservice.domain.entities.Expense;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExpenseManagerService {
    Flux<Expense> findAll();
    Mono<Expense> save(Expense expense);
    Mono<Expense> findById(String id);
    Mono<Void> delete(String id);
}
