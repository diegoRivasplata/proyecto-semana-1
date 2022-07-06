package com.proyectosemana1.cuentasbancariasservice.presentation.controllers;

import com.proyectosemana1.cuentasbancariasservice.application.services.BusinessAccountManagerService;
import com.proyectosemana1.cuentasbancariasservice.application.services.PersonalAccountManagerService;
import com.proyectosemana1.cuentasbancariasservice.domain.entities.BusinessAccount;
import com.proyectosemana1.cuentasbancariasservice.domain.entities.PersonalAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/apibank/personal-account")
public class PersonalAccountManagerController {
    @Autowired
    private PersonalAccountManagerService personalAccountService;

    @GetMapping
    public Flux<PersonalAccount> findAll(){
        return personalAccountService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<PersonalAccount> findById(@PathVariable String id){
        return personalAccountService.findById(id);
    }

    @PostMapping
    public Mono<PersonalAccount> save(@RequestBody PersonalAccount personalAccount){
        return personalAccountService.save(personalAccount);
    }

    @PutMapping("/{id}")
    public Mono<PersonalAccount> update(@PathVariable String id,@RequestBody PersonalAccount personalAccount){
        return personalAccountService.update(id,personalAccount);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return personalAccountService.delete(id);
    }
}
