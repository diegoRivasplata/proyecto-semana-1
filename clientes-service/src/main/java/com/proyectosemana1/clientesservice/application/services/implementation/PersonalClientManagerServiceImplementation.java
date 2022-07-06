package com.proyectosemana1.clientesservice.application.services.implementation;

import com.proyectosemana1.clientesservice.application.services.PersonalClientManagerService;
import com.proyectosemana1.clientesservice.domain.entities.PersonalClient;
import com.proyectosemana1.clientesservice.infrastructure.repository.PersonalClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalClientManagerServiceImplementation implements PersonalClientManagerService {
    /**
     * Repositorio de PersonalClient.
     * Se usa para invocar los metodos del repositorio.
     * Valores válidos: Autowired.
     * Error NullPointer.
     */
    @Autowired
    private PersonalClientRepository personalClientRepository;
    /**
     * Buscar todos los clientes.
     * Busca todos los personalClient registrados en el sistema.
     * @return Un Flux con una lista de cliente.
     * @throws RuntimeException en caso ocurra algun error
     */
    public Flux<PersonalClient> findAll() {
        return personalClientRepository.findAll(
                Sort.by(Sort.Direction.ASC, "lastName"));
    }
    /**
     * Guarda un cliente.
     * Guarda un business cliente en base de datos.
     * @param personalClient un cliente.
     * @return Un Mono con un businessCliente.
     * @throws RuntimeException en caso ocurra algun error o los atributos no sean validos.
     */
    public Mono<PersonalClient> save(final PersonalClient personalClient) {
        if (personalClient.validateDni()) {
            return personalClientRepository.save(personalClient);
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
    public Mono<PersonalClient> findById(final String id) {
        return personalClientRepository.findById(id);
    }
    /**
     * Actualiza un cliente.
     * Actualiza los datos de un cliente dentro de la base de datos.
     * @param id id del business client.
     * @param personalClient un cliente con los datos actualizados.
     * @return Un Mono con un business client.
     * @throws RuntimeException en caso ocurra algun error.
     */
    public Mono<PersonalClient> update(final String id, final PersonalClient personalClient) {
        return personalClientRepository.findById(id).doOnNext(client -> {
                    if (client == null) {
                        throw new RuntimeException("No se encontró el cliente con id " + id + ".");
                    }
                })
                .map((client) -> {
                    client.setName(personalClient.getName());
                    client.setLastName(personalClient.getLastName());
                    client.setCity(personalClient.getCity());
                    client.setAddress(personalClient.getAddress());
                    client.setPhoneNumber(personalClient.getPhoneNumber());
                    client.setEmail(personalClient.getEmail());
                    client.setCreditScore(personalClient.getCreditScore());
                    return client;
                }).flatMap(client -> personalClientRepository.save(client));
    }
    /**
     * Elimina un cliente.
     * Busca un cliente por su id y lo elimina.
     * @param id id del cliente a eliminar
     * @return Un mono vacio.
     * @throws RuntimeException en caso ocurra algun error.
     */
    public Mono<Void> delete(final String id) {
        return personalClientRepository.deleteById(id);
    }

}
