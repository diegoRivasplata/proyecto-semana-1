package com.proyectosemana1.creditosservice.infrastructure.repository;

import com.proyectosemana1.creditosservice.domain.entities.Expense;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ExpenseRepository extends ReactiveMongoRepository<Expense, String> {
    Flux<Expense> findAllByCreditCardId(String creditCardId);
}
