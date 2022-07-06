package com.proyectosemana1.creditosservice.application.services;

import com.proyectosemana1.creditosservice.application.OperationsService;
import com.proyectosemana1.creditosservice.domain.entities.Credit;
import com.proyectosemana1.creditosservice.domain.entities.CreditCard;
import com.proyectosemana1.creditosservice.domain.entities.Expense;
import com.proyectosemana1.creditosservice.domain.entities.Payment;
import com.proyectosemana1.creditosservice.infrastructure.repository.CreditCardRepository;
import com.proyectosemana1.creditosservice.infrastructure.repository.CreditRepository;
import com.proyectosemana1.creditosservice.infrastructure.repository.ExpenseRepository;
import com.proyectosemana1.creditosservice.infrastructure.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class OperationsServiceImplementation implements OperationsService {

    @Autowired
    private CreditCardRepository _creditCardRepository;
    @Autowired
    private CreditRepository _creditRepository;
    @Autowired
    private ExpenseRepository _expenseRepository;
    @Autowired
    private PaymentRepository _paymentRepository;

    @Override
    public Mono<Payment> makePayment(Payment payment) {
        if(payment.getTypeOfProduct().equalsIgnoreCase("creditCard")){
            _creditCardRepository.findById(payment.getCreditProductId())
                    .doOnNext(card -> {
                        if(card == null){
                            throw new RuntimeException("No se encontró la tarjeta con id " + payment.getCreditProductId() + ".");
                        }
                    }).map(card -> {
                        if(payment.validateAmount() && payment.getAmount() <= card.getTotalDebt()){
                            _creditCardRepository.save(calculateNewValuesAfterPayment(card, payment.getAmount()));
                            return _paymentRepository.save(payment);
                        }
                        throw new RuntimeException("La cantidad ingresada no es valida o es mayor a la deuda actual");
                    });
        } else if (payment.getTypeOfProduct().equalsIgnoreCase("credit")) {
            _creditRepository.findById(payment.getCreditProductId())
                    .doOnNext(credit -> {
                        if(credit == null){
                            throw new RuntimeException("No se encontró el crédito con id " + payment.getCreditProductId() + ".");
                        }
                    }).map(credit -> {
                        if(payment.validateAmount() && payment.getAmount() <= credit.getTotalDebt()){
                            _creditRepository.save(calculateNewValuesAfterPayment(credit, payment.getAmount()));
                            return _paymentRepository.save(payment);
                        }
                        throw new RuntimeException("La cantidad ingresada no es valida o es mayor a la deuda actual");
                    });
        }
        return Mono.empty();
    }

    @Override
    public Mono<Expense> useCreditCard(Expense expense) {

        _creditCardRepository.findById(expense.getCreditCardId())
                .doOnNext(card -> {
                    if (card == null) {
                        throw new RuntimeException("No se encontró la tarjeta con id " + expense.getCreditCardId() + ".");
                    }
                }).map(card -> {
                    if(isExpenseValid(expense) && (expense.getAmount() <= card.getAvailableBalance())){{
                        card.setAvailableBalance(card.getAvailableBalance() - expense.getAmount());
                        _creditCardRepository.save(card);
                        return _expenseRepository.save(expense);
                    }}
                    throw new RuntimeException("Argumentos invalidos");
                });
        /*
        _creditCardRepository.findById(expense.getCreditCardId())
                .doOnNext(card -> {
                    if(card == null){
                        throw new RuntimeException("No se encontró la tarjeta con id " + expense.getCreditCardId() + ".");
                    }
                    if(isExpenseValid(expense) && (expense.getAmount() < card.getAvailableBalance())){
                        card.setAvailableBalance(card.getAvailableBalance() - expense.getAmount());
                        _creditCardRepository.save(card);
                    }
                    else{
                        throw new RuntimeException("Argumentos invalidos");
                    }
                });
        return _expenseRepository.save(expense);
        */
        return Mono.empty();
    }

    @Override
    public Mono<Map> checkAvailableBalance(String creditCardId) {

        Mono<Map> availableBalanceMono = _creditCardRepository.findById(creditCardId)
                .doOnNext(card -> {
                    if(card == null){
                        throw new RuntimeException("No se encontró la tarjeta con id " +creditCardId + ".");
                    }
                }).map(card -> {
                    return Mono.just(new HashMap<String,Float>(){
                        {
                            put(card.getCreditCardNumber(),card.getAvailableBalance());
                        }
                    });
                }).cast(Map.class);
        return availableBalanceMono;
    }

    @Override
    public Flux<?> checkAllOperationsOnCreditCard(String creditCardId) {
        return Flux.just(_expenseRepository.findAllByCreditCardId(creditCardId),_paymentRepository.findAllByCreditProductId(creditCardId));
    }

    private boolean isExpenseValid(Expense expense){
        return (expense.validateAmount() && expense.validateCurrency() && expense.validateNumberOfInstallments());
    }

    private CreditCard calculateNewValuesAfterPayment(CreditCard creditCard, float paymentAmount){
        creditCard.setAvailableBalance(creditCard.getAvailableBalance() - paymentAmount);
        creditCard.setTotalDebt(creditCard.getTotalDebt() + paymentAmount);
        return creditCard;
    }
    private Credit calculateNewValuesAfterPayment(Credit credit, float paymentAmount){
        credit.setTotalDebt(credit.getTotalDebt() + paymentAmount);
        return credit;
    }

}
