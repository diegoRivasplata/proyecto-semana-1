package com.proyectosemana1.clientesservice.presentation.controllers;

import com.proyectosemana1.clientesservice.application.services.BusinessClientManagerService;
import com.proyectosemana1.clientesservice.domain.entities.BusinessClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/apibank/business-client")
public class BusinessClientManageController {

    @Autowired
    private BusinessClientManagerService businessManageService;

    @GetMapping
    public Flux<BusinessClient> findAll() {
        return businessManageService.findAll();
    }

    @GetMapping("/{id}")
    private Mono<BusinessClient> findById(@PathVariable final String id) {
        return businessManageService.findById(id);
    }

    @PostMapping
    public Mono<BusinessClient> save(@RequestBody final BusinessClient client) {
        return businessManageService.save(client);
    }

    @PutMapping("/{id}")
    public Mono<BusinessClient> update(@PathVariable final String id,
                                       @RequestBody final BusinessClient businessClient) {
        return businessManageService.update(id, businessClient);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable final String id) {
        return businessManageService.delete(id);
    }
}
