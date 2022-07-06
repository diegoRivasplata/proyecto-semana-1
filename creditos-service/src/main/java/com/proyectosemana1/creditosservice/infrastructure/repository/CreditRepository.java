package com.proyectosemana1.creditosservice.infrastructure.repository;

import com.proyectosemana1.creditosservice.domain.entities.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends ReactiveMongoRepository<Credit, String> {
}
