package com.proyectosemana1.cuentasbancariasservice.application.services;

import com.proyectosemana1.cuentasbancariasservice.domain.entities.Transaction;
import reactor.core.publisher.Mono;

public interface OperationsService {
    Mono<Transaction> doDepositOnPersonalAccount(Transaction transaction);
    Mono<Transaction> doDepositOnBusinessAccount(Transaction transaction);
    Mono<Transaction> doWithdrawal(Transaction transaction);
}
