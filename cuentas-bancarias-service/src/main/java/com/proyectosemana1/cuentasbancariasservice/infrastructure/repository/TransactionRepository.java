package com.proyectosemana1.cuentasbancariasservice.infrastructure.repository;

import com.proyectosemana1.cuentasbancariasservice.domain.entities.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {
}
