package com.proyectosemana1.cuentasbancariasservice.application.services.webclients;

import com.proyectosemana1.cuentasbancariasservice.domain.entities.BusinessClient;
import com.proyectosemana1.cuentasbancariasservice.domain.entities.PersonalClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceWebClient {

    private final WebClient webClient;

    public ClientServiceWebClient(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082/apibank").build();
    }

    public Mono<BusinessClient> findBusinessClientById(String id){
        return this.webClient.get().uri("/business-client/{id}",id)
                .retrieve().bodyToMono(BusinessClient.class);
    }

    public Mono<PersonalClient> findPersonalClientById(String id){
        return this.webClient.get().uri("/personal-client/{id}",id)
                .retrieve().bodyToMono(PersonalClient.class);
    }

}
