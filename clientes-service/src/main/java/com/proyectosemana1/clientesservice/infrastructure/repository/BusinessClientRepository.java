package com.proyectosemana1.clientesservice.infrastructure.repository;

import com.proyectosemana1.clientesservice.domain.entities.BusinessClient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessClientRepository extends ReactiveMongoRepository<BusinessClient, String> {
}
