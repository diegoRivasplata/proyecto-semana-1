package com.proyectosemana1.clientesservice.application.services.implementation;

import com.proyectosemana1.clientesservice.application.services.BusinessClientManagerService;
import com.proyectosemana1.clientesservice.domain.entities.BusinessClient;
import com.proyectosemana1.clientesservice.domain.entities.Client;
import com.proyectosemana1.clientesservice.infrastructure.repository.BusinessClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BusinessClientManagerServiceImplementation implements BusinessClientManagerService {
    /**
     * Repositorio de BusinessClient.
     * Se usa para invocar los metodos del repositorio.
     * Valores válidos: Autowired.
     * Error NullPointer.
     */
    @Autowired
    private BusinessClientRepository businessClientRepository;

    /**
     * Buscar todos los clientes.
     * Busca todos los businessclient registrados en el sistema.
     * @return Un Flux con una lista de cliente.
     * @throws RuntimeException en caso ocurra algun error
     */
    public Flux<BusinessClient> findAll() {
        return businessClientRepository.findAll();
    }
    /**
     * Guarda un cliente.
     * Guarda un business cliente en base de datos.
     * @param businessClient un cliente.
     * @return Un Mono con un businessCliente.
     * @throws RuntimeException en caso ocurra algun error o los atributos no sean validos.
     */
    public Mono<BusinessClient> save(final BusinessClient businessClient) {
        if (businessClient.validateRucNumber()) {
            return businessClientRepository.save(businessClient);
        }
        throw new RuntimeException("Ocurrió un error al validar los atributos.");
    }
    /**
     * Busca un cliente.
     * Busca un cliente por su id y lo retorna.
     * @param id id del business client.
     * @return Un Mono con un business client.
     * @throws RuntimeException en caso ocurra algun error.
     */
    public Mono<BusinessClient> findById(final String id) {
        return businessClientRepository.findById(id);
    }
    /**
     * Actualiza un cliente.
     * Actualiza los datos de un cliente dentro de la base de datos.
     * @param id id del business client.
     * @param businessClient un cliente con los datos actualizados.
     * @return Un Mono con un business client.
     * @throws RuntimeException en caso ocurra algun error.
     */
    public Mono<BusinessClient> update(final String id,
                                       final BusinessClient businessClient) {
        return businessClientRepository.findById(id).doOnNext(client -> {
                    if (client == null) {
                        throw new RuntimeException("No se encontró el cliente con id " + id + ".");
                    }
                })
                .map((client) -> {
                    client.setCompanyName(businessClient.getCompanyName());
                    client.setCity(businessClient.getCity());
                    client.setAddress(businessClient.getAddress());
                    client.setPhoneNumber(businessClient.getPhoneNumber());
                    client.setEmail(businessClient.getEmail());
                    client.setCreditScore(businessClient.getCreditScore());
                    return client;
                }).flatMap(client -> businessClientRepository.save(client));
    }
    /**
     * Elimina un cliente.
     * Busca un cliente por su id y lo elimina.
     * @param id id del cliente a eliminar
     * @return Un mono vacio.
     * @throws RuntimeException en caso ocurra algun error.
     */
    public Mono<Void> delete(final String id) {
        return businessClientRepository.deleteById(id);
    }
    private Client mapToClient(BusinessClient businessClient){
        Client client = new Client(null,
                businessClient.getPhoneNumber(),
                businessClient.getCity(),
                businessClient.getAddress(),
                businessClient.getEmail(),
                businessClient.getCreditScore(),
                businessClient.getCreationDate(),
                businessClient.isActive());
        return client;
    }
}
