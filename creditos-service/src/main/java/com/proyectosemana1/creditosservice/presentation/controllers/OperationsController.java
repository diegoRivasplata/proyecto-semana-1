package com.proyectosemana1.creditosservice.presentation.controllers;

import com.proyectosemana1.creditosservice.application.OperationsService;
import com.proyectosemana1.creditosservice.domain.entities.Expense;
import com.proyectosemana1.creditosservice.domain.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/apibank/operation")
public class OperationsController {

    @Autowired
    private OperationsService _operationsService;

    @PostMapping("/pay-credit")
    public Mono<Payment> makePayment(@RequestBody Payment payment){
        return _operationsService.makePayment(payment);
    }

    @PostMapping("/use-card")
    public Mono<Expense> useCreditCard(@RequestBody Expense expense){
        return _operationsService.useCreditCard(expense);
    }

    @GetMapping("/check-balance/{creditCardId}")
    public Mono<Map> checkAvailableBalance(@PathVariable String creditCardId){
        return _operationsService.checkAvailableBalance(creditCardId);
    }

    @GetMapping("/check-operations/{creditCardId}")
    public Flux<?> checkAllOperationsOnCreditCard(@PathVariable String creditCardId){
        return _operationsService.checkAllOperationsOnCreditCard(creditCardId);
    }
}
