package com.proyectosemana1.creditosservice.presentation.controllers;

import com.proyectosemana1.creditosservice.application.CreditCardManagerService;
import com.proyectosemana1.creditosservice.domain.entities.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("")
public class CreditCardManagerController {

    @Autowired
    private CreditCardManagerService _creditCardManagerService;

    @GetMapping
    public Flux<CreditCard> findAll(){
        return _creditCardManagerService.findAll();
    }

    @GetMapping("/{id}")
    private Mono<CreditCard> findById(@PathVariable String id){
        return _creditCardManagerService.findById(id);
    }

    @PostMapping
    public Mono<CreditCard> save(@RequestBody CreditCard creditCard){
        return _creditCardManagerService.save(creditCard);
    }

    @PutMapping("/{id}")
    public Mono<CreditCard> update(@PathVariable String id, @RequestBody CreditCard creditCard){
        return _creditCardManagerService.update(id,creditCard);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return _creditCardManagerService.delete(id);
    }

}
