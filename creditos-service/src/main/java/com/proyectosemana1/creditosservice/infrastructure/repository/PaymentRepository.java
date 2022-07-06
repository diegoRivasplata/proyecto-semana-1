package com.proyectosemana1.creditosservice.infrastructure.repository;

import com.proyectosemana1.creditosservice.domain.entities.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PaymentRepository extends ReactiveMongoRepository<Payment, String> {
    Flux<Payment> findAllByCreditProductId(String creditProductId);
}
