package com.proyectosemana1.cuentasbancariasservice.presentation.controllers;

import com.proyectosemana1.cuentasbancariasservice.application.services.OperationsService;
import com.proyectosemana1.cuentasbancariasservice.domain.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/apibank/transaction")
public class OperationsController {

    @Autowired
    private OperationsService operationsService;

    @PostMapping("/deposit-on-personal")
    public Mono<Transaction> doDepositOnPersonalAccount(@RequestBody Transaction transaction){
        return operationsService.doDepositOnPersonalAccount(transaction);
    }

    @PostMapping("/deposit-on-business")
    public Mono<Transaction> doDepositOnBusinessAccount(@RequestBody Transaction transaction){
        return operationsService.doDepositOnBusinessAccount(transaction);
    }
}
