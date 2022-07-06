package com.proyectosemana1.creditosservice.application.services;

import com.proyectosemana1.creditosservice.application.CreditManagerService;
import com.proyectosemana1.creditosservice.domain.entities.Credit;
import com.proyectosemana1.creditosservice.infrastructure.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditManagerServiceImplementation implements CreditManagerService {

    @Autowired
    private CreditRepository _creditRepository;

    @Override
    public Flux<Credit> findAll() {
        return _creditRepository.findAll();
    }

    @Override
    public Mono<Credit> save(Credit credit) {
        return _creditRepository.save(credit);
    }

    @Override
    public Mono<Credit> findById(String id) {
        return _creditRepository.findById(id);
    }

    @Override
    public Mono<Credit> update(String id, Credit credit) {
        return _creditRepository.findById(id).doOnNext(c -> {
                    if(c == null){
                        throw new RuntimeException("No se encontró la tarjeta con id " + id + ".");
                    }
                })
                .map((c) -> {
                    c.setNumberOfInstallments(credit.getNumberOfInstallments());
                    c.setInstallmentAmount(credit.getInstallmentAmount());
                    return c;
                }).flatMap(c -> _creditRepository.save(c));
    }

    @Override
    public Mono<Void> delete(String id) {
        return _creditRepository.deleteById(id);
    }
}
