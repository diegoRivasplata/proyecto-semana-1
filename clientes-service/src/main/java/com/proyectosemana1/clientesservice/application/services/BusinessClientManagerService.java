package com.proyectosemana1.clientesservice.application.services;

import com.proyectosemana1.clientesservice.domain.entities.BusinessClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BusinessClientManagerService {

    Flux<BusinessClient> findAll();
    Mono<BusinessClient> save(BusinessClient businessClient);
    Mono<BusinessClient> findById(String id);
    Mono<BusinessClient> update(String id, BusinessClient businessClient);
    Mono<Void> delete(String id);
}
