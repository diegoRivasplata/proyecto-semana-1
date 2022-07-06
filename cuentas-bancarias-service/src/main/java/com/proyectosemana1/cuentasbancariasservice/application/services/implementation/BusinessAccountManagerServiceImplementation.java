package com.proyectosemana1.cuentasbancariasservice.application.services.implementation;

import com.proyectosemana1.cuentasbancariasservice.application.services.BusinessAccountManagerService;
import com.proyectosemana1.cuentasbancariasservice.domain.entities.BusinessAccount;
import com.proyectosemana1.cuentasbancariasservice.infrastructure.repository.BusinessAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BusinessAccountManagerServiceImplementation implements BusinessAccountManagerService {

    @Autowired
    private BusinessAccountRepository businessAccountRepository;

    @Override
    public Flux<BusinessAccount> findAll() {
        return businessAccountRepository.findAll();
    }

    @Override
    public Mono<BusinessAccount> findById(String id) {
        return businessAccountRepository.findById(id);
    }

    @Override
    public Mono<BusinessAccount> save(BusinessAccount businessBankAccount) {
        return businessAccountRepository.save(businessBankAccount);
    }

    @Override
    public Mono<BusinessAccount> update(String id, BusinessAccount businessBankAccount) {
        return businessAccountRepository.findById(id).doOnNext(account -> {
            if(account == null){
                throw new RuntimeException("No se encontro la cuenta con id: " + id);
            }
        }).map(account -> {
            account.setAccountType(businessBankAccount.getAccountType());
            account.setCommissionFree(businessBankAccount.isCommissionFree());
            account.setMonthlyMovementsAllowed(businessBankAccount.getMonthlyMovementsAllowed());
            return account;
        }).flatMap(account -> businessAccountRepository.save(account));
    }

    @Override
    public Mono<Void> delete(String id) {
        return businessAccountRepository.deleteById(id);
    }
}
