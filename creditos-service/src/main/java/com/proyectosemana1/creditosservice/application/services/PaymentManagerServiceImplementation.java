package com.proyectosemana1.creditosservice.application.services;

import com.proyectosemana1.creditosservice.application.PaymentManagerService;
import com.proyectosemana1.creditosservice.domain.entities.Payment;
import com.proyectosemana1.creditosservice.infrastructure.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentManagerServiceImplementation implements PaymentManagerService {

    @Autowired
    private PaymentRepository _paymentRepository;

    @Override
    public Flux<Payment> findAll() {
        return _paymentRepository.findAll();
    }

    @Override
    public Mono<Payment> save(Payment payment) {
        return _paymentRepository.save(payment);
    }

    @Override
    public Mono<Payment> findById(String id) {
        return _paymentRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return _paymentRepository.deleteById(id);
    }
}
