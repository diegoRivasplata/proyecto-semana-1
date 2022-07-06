package com.proyectosemana1.creditosservice.application;

import com.proyectosemana1.creditosservice.domain.entities.Expense;
import com.proyectosemana1.creditosservice.domain.entities.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface OperationsService {
    Mono<Payment> makePayment(Payment payment);
    Mono<Expense> useCreditCard(Expense expense);
    Mono<Map> checkAvailableBalance(String creditCardId);
    Flux<?> checkAllOperationsOnCreditCard(String creditCardId);
}
