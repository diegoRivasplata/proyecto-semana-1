package com.proyectosemana1.cuentasbancariasservice.application.services.implementation;

import com.proyectosemana1.cuentasbancariasservice.application.services.OperationsService;
import com.proyectosemana1.cuentasbancariasservice.domain.entities.Transaction;
import com.proyectosemana1.cuentasbancariasservice.infrastructure.repository.BusinessAccountRepository;
import com.proyectosemana1.cuentasbancariasservice.infrastructure.repository.PersonalAccountRepository;
import com.proyectosemana1.cuentasbancariasservice.infrastructure.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OperationsServiceImplementation implements OperationsService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PersonalAccountRepository personalAccountRepository;
    @Autowired
    private BusinessAccountRepository businessAccountRepository;


    @Override
    public Mono<Transaction> doDepositOnPersonalAccount(Transaction transaction) {
        return personalAccountRepository.findById(transaction.getAccountId())
                .switchIfEmpty(Mono.error(new Exception("No se encontro la cuenta con id: "+ transaction.getAccountId())))
                .map(account -> {
                    account.setAvailableBalance(account.getAvailableBalance() + transaction.getAmount());
                    account.setNumberOfMovementsMade(account.getNumberOfMovementsMade() + 1);
                    return account;
                }).flatMap(account -> personalAccountRepository.save(account))
                .flatMap(t-> transactionRepository.save(transaction));
    }

    @Override
    public Mono<Transaction> doDepositOnBusinessAccount(Transaction transaction) {
        return businessAccountRepository.findById(transaction.getAccountId())
                .switchIfEmpty(Mono.error(new Exception("No se encontro la cuenta con id: "+ transaction.getAccountId())))
                .map(account -> {
                    account.setAvailableBalance(account.getAvailableBalance() + transaction.getAmount());
                    account.setNumberOfMovementsMade(account.getNumberOfMovementsMade() + 1);
                    return account;
                }).flatMap(account -> businessAccountRepository.save(account))
                .flatMap(t-> transactionRepository.save(transaction));
    }

    @Override
    public Mono<Transaction> doWithdrawal(Transaction transaction) {
        return null;
    }
}
