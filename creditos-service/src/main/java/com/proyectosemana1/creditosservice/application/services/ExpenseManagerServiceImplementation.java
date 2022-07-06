package com.proyectosemana1.creditosservice.application.services;

import com.proyectosemana1.creditosservice.application.ExpenseManagerService;
import com.proyectosemana1.creditosservice.domain.entities.Expense;
import com.proyectosemana1.creditosservice.infrastructure.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExpenseManagerServiceImplementation implements ExpenseManagerService {

    @Autowired
    private ExpenseRepository _expenseRepository;

    @Override
    public Flux<Expense> findAll() {
        return _expenseRepository.findAll();
    }

    @Override
    public Mono<Expense> save(Expense expense) {
        return _expenseRepository.save(expense);
    }

    @Override
    public Mono<Expense> findById(String id) {
        return _expenseRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return _expenseRepository.deleteById(id);
    }
}
