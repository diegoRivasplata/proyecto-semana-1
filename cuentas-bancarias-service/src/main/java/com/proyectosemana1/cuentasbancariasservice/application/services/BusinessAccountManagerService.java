package com.proyectosemana1.cuentasbancariasservice.application.services;

import com.proyectosemana1.cuentasbancariasservice.domain.entities.BusinessAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BusinessAccountManagerService {
    Flux<BusinessAccount> findAll();
    Mono<BusinessAccount> findById(String id);
    Mono<BusinessAccount> save(BusinessAccount businessBankAccount);
    Mono<BusinessAccount> update(String id, BusinessAccount businessBankAccount);
    Mono<Void> delete(String id);
}
