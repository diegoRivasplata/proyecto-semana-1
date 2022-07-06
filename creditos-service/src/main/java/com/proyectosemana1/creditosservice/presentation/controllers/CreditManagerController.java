package com.proyectosemana1.creditosservice.presentation.controllers;

import com.proyectosemana1.creditosservice.application.CreditCardManagerService;
import com.proyectosemana1.creditosservice.application.CreditManagerService;
import com.proyectosemana1.creditosservice.domain.entities.Credit;
import com.proyectosemana1.creditosservice.domain.entities.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("")
public class CreditManagerController {

    @Autowired
    private CreditManagerService _creditManagerService;

    @GetMapping
    public Flux<Credit> findAll(){
        return _creditManagerService.findAll();
    }

    @GetMapping("/{id}")
    private Mono<Credit> findById(@PathVariable String id){
        return _creditManagerService.findById(id);
    }

    @PostMapping
    public Mono<Credit> save(@RequestBody Credit credit){
        return _creditManagerService.save(credit);
    }

    @PutMapping("/{id}")
    public Mono<Credit> update(@PathVariable String id, @RequestBody Credit credit){
        return _creditManagerService.update(id,credit);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return _creditManagerService.delete(id);
    }
}
