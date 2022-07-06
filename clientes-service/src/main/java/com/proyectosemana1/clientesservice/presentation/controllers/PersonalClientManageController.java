package com.proyectosemana1.clientesservice.presentation.controllers;

import com.proyectosemana1.clientesservice.application.services.PersonalClientManagerService;
import com.proyectosemana1.clientesservice.domain.entities.BusinessClient;
import com.proyectosemana1.clientesservice.domain.entities.PersonalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/apibank/personal-client")
public class PersonalClientManageController {

    @Autowired
    private PersonalClientManagerService personalClientManagerService;

    @GetMapping
    public Flux<PersonalClient> findAll() {
        return personalClientManagerService.findAll();
    }

    @GetMapping("/{id}")
    private Mono<PersonalClient> findById(@PathVariable final String id) {
        return personalClientManagerService.findById(id);
    }

    @PostMapping
    public Mono<PersonalClient> save(@RequestBody final PersonalClient client) {
        return personalClientManagerService.save(client);
    }

    @PutMapping("/{id}")
    public Mono<PersonalClient> update(@PathVariable final String id, @RequestBody final PersonalClient personalClient) {
        return personalClientManagerService.update(id, personalClient);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable final String id) {
        return personalClientManagerService.delete(id);
    }
}