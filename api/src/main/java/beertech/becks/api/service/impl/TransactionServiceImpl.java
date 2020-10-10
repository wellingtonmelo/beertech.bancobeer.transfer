package beertech.becks.api.service.impl;

import beertech.becks.api.entities.Transaction;
import beertech.becks.api.repositories.TransactionRepository;
import beertech.becks.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

    @Override
    public BigDecimal getBalance(){
        BigDecimal result = new BigDecimal(0);
        List<Transaction> transactionList = transactionRepository.findAll();

        for(Transaction transaction : transactionList){
            result = result.add(transaction.getValueTransaction());
        }

        return result;
    }

}
