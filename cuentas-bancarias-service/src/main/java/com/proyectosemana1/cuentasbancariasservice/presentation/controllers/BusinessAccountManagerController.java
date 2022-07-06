package com.proyectosemana1.cuentasbancariasservice.presentation.controllers;

import com.proyectosemana1.cuentasbancariasservice.application.services.BusinessAccountManagerService;
import com.proyectosemana1.cuentasbancariasservice.domain.entities.BusinessAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/apibank/business-account")
public class BusinessAccountManagerController {

    @Autowired
    private BusinessAccountManagerService businessAccountService;

    @GetMapping
    public Flux<BusinessAccount> findAll(){
        return businessAccountService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<BusinessAccount> findById(@PathVariable String id){
        return businessAccountService.findById(id);
    }

    @PostMapping
    public Mono<BusinessAccount> save(@RequestBody BusinessAccount businessAccount){
        return businessAccountService.save(businessAccount);
    }

    @PutMapping("/{id}")
    public Mono<BusinessAccount> update(@PathVariable String id,@RequestBody BusinessAccount businessAccount){
        return businessAccountService.update(id,businessAccount);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return businessAccountService.delete(id);
    }
}
