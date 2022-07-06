package com.proyectosemana1.creditosservice.application.services;

import com.proyectosemana1.creditosservice.application.CreditCardManagerService;
import com.proyectosemana1.creditosservice.domain.entities.CreditCard;
import com.proyectosemana1.creditosservice.infrastructure.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditCardManagerServiceImplementation implements CreditCardManagerService {

    @Autowired
    private CreditCardRepository _creditCardRepository;

    @Override
    public Flux<CreditCard> findAll() {
        return _creditCardRepository.findAll();
    }

    @Override
    public Mono<CreditCard> save(CreditCard creditCard) {
        return _creditCardRepository.save(creditCard);
    }

    @Override
    public Mono<CreditCard> findById(String id) {
        return _creditCardRepository.findById(id);
    }

    @Override
    public Mono<CreditCard> update(String id, CreditCard creditCard) {
        return _creditCardRepository.findById(id).doOnNext(card -> {
                    if(card == null){
                        throw new RuntimeException("No se encontró la tarjeta con id " + id + ".");
                    }
                })
                .map((card) -> {
                    card.setAbroadShoppingAllowed(creditCard.isAbroadShoppingAllowed());
                    card.setOverdraftAllowed(creditCard.isOverdraftAllowed());
                    card.setOverdraftFee(creditCard.getOverdraftFee());
                    card.setCreditAmount(creditCard.getCreditAmount());
                    return card;
                }).flatMap(card -> _creditCardRepository.save(card));
    }

    @Override
    public Mono<Void> delete(String id) {
        return _creditCardRepository.deleteById(id);
    }
}
