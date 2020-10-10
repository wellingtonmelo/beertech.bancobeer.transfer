package beertech.becks.api.service;

import beertech.becks.api.entities.Balance;
import beertech.becks.api.entities.Transaction;
import beertech.becks.api.tos.TransactionRequestTO;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    Transaction createTransaction(TransactionRequestTO transactionTO);

    Balance getBalance();

}
