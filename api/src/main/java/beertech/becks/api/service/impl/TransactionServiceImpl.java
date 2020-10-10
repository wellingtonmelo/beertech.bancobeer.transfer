package beertech.becks.api.service.impl;

import beertech.becks.api.entities.Transaction;
import beertech.becks.api.repositories.TransactionRepository;
import beertech.becks.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static beertech.becks.api.model.TypeOperation.SAQUE;
import static java.time.ZonedDateTime.now;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {

        if (SAQUE.equals(transaction.getTypeOperation())){
            transaction.setValueTransaction(transaction.getValueTransaction().negate());
        }

        transaction.setDateTime(now());
        return transactionRepository.save(transaction);
    }

}
