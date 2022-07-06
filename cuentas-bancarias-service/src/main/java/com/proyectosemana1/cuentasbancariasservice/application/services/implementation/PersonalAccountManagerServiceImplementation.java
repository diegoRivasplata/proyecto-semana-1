package com.proyectosemana1.cuentasbancariasservice.application.services.implementation;

import com.proyectosemana1.cuentasbancariasservice.application.services.PersonalAccountManagerService;
import com.proyectosemana1.cuentasbancariasservice.application.services.webclients.ClientServiceWebClient;
import com.proyectosemana1.cuentasbancariasservice.domain.entities.PersonalAccount;
import com.proyectosemana1.cuentasbancariasservice.infrastructure.repository.PersonalAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalAccountManagerServiceImplementation implements PersonalAccountManagerService {

    @Autowired
    private PersonalAccountRepository personalAccountRepository;

    @Autowired
    private ClientServiceWebClient clientServiceWebClient;

    @Override
    public Flux<PersonalAccount> findAll() {
        return personalAccountRepository
                .findAll(Sort.by(Sort.Direction.DESC, "openingDate"));
    }

    @Override
    public Mono<PersonalAccount> findById(String id) {
        return personalAccountRepository.findById(id);
    }

    @Override
    public Mono<PersonalAccount> save(PersonalAccount personalAccount) {
        clientServiceWebClient.findPersonalClientById(personalAccount.getClientId())
                .subscribe(client -> {
                    if(client != null){
                        System.out.println(client.toString());
                    }

                });
   }

    @Override
    public Mono<PersonalAccount> update(String id, PersonalAccount personalAccount) {
        return personalAccountRepository.findById(id).doOnNext(account -> {
            if(account == null){
                throw new RuntimeException("No se encontro la cuenta con id: "+ id);
            }
        }).map(account -> {
            account.setAccountType(personalAccount.getAccountType());
            account.setCommissionFree(personalAccount.isCommissionFree());
            account.setMonthlyMovementsAllowed(personalAccount.getMonthlyMovementsAllowed());
            return account;
        }).flatMap(account -> personalAccountRepository.save(account));
    }

    @Override
    public Mono<Void> delete(String id) {
        return personalAccountRepository.deleteById(id);
    }
}
