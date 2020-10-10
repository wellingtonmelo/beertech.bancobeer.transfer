package beertech.becks.api.service;

import beertech.becks.api.entities.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Transaction transaction);

    BigDecimal getBalance();

}
