package beertech.becks.api.service.impl;

import beertech.becks.api.entities.Account;
import beertech.becks.api.entities.Balance;
import beertech.becks.api.entities.Transaction;
import beertech.becks.api.exception.ValidationException;
import beertech.becks.api.repositories.AccountRepository;
import beertech.becks.api.repositories.TransactionRepository;
import beertech.becks.api.service.TransactionService;
import beertech.becks.api.tos.AccountRequestTO;
import beertech.becks.api.tos.TransactionRequestTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static beertech.becks.api.model.TypeOperation.DEPOSITO;
import static beertech.becks.api.model.TypeOperation.SAQUE;
import static beertech.becks.api.model.TypeOperation.TRANSFERENCIA;
import static java.time.ZonedDateTime.now;

@Service
public class TransactionServiceImpl implements TransactionService {

  private TransactionRepository transactionRepository;
  private AccountRepository accountRepository;

  @Autowired
  public TransactionServiceImpl(
      TransactionRepository transactionRepository, AccountRepository accountRepository) {
    this.transactionRepository = transactionRepository;
    this.accountRepository = accountRepository;
  }

  @Override
  public void processOperation(TransactionRequestTO transactionTO) {
    if (TRANSFERENCIA.getDescription().equals(transactionTO.getOperation())) {
      createTransfer(transactionTO);
    } else {
      createTransaction(transactionTO);
    }
  }

  @Override
  public Balance getBalance(String document) {
    Balance balance = new Balance();
    BigDecimal sum =
        transactionRepository.findAll().stream()
            .filter(transaction -> transaction.getAccountDocument().equals(document))
            .map(Transaction::getValueTransaction)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    balance.setBalance(sum);
    return balance;
  }

  @Override
  public Account createAccount(AccountRequestTO accountRequest) {
    List<Account> allAccount = accountRepository.findAll();

    allAccount.forEach(
        account -> {
          if (account.getCpf().equals(accountRequest.getCpf()))
            throw new ValidationException(
                "404", "Customer already registered with the CPF informed");
        });
    Account account = new Account();
    account.setCpf(accountRequest.getCpf());
    account.setName(accountRequest.getName());
    account.setEmail(accountRequest.getEmail());

    return accountRepository.save(account);
  }

  private void createTransaction(TransactionRequestTO transactionTO) {
    Transaction transaction = new Transaction();

    if (SAQUE.getDescription().equals(transactionTO.getOperation())) {
      transaction.setValueTransaction(transactionTO.getValue().negate());
      transaction.setTypeOperation(SAQUE);
    } else {
      transaction.setValueTransaction(transactionTO.getValue());
      transaction.setTypeOperation(DEPOSITO);
    }

    transaction.setAccountDocument(transactionTO.getAccountDocument());
    transaction.setDateTime(now());
    transactionRepository.save(transaction);
  }

  private void createTransfer(TransactionRequestTO transactionRequestTO) {
    List<Transaction> transactions = new ArrayList<>();

    Transaction transaction = new Transaction();
    transaction.setAccountDocument(transactionRequestTO.getFromAccountDocument());
    transaction.setValueTransaction(transactionRequestTO.getValue().negate());
    transaction.setDateTime(now());
    transaction.setTypeOperation(TRANSFERENCIA);
    transactions.add(transaction);

    transaction = new Transaction();
    transaction.setAccountDocument(transactionRequestTO.getToAccountDocument());
    transaction.setValueTransaction(transactionRequestTO.getValue());
    transaction.setDateTime(now());
    transaction.setTypeOperation(TRANSFERENCIA);
    transactions.add(transaction);

    transactionRepository.saveAll(transactions);
  }
}
