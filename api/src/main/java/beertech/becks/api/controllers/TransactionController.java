package beertech.becks.api.controllers;

import beertech.becks.api.entities.Transaction;
import beertech.becks.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
		return ok(transactionService.createTransaction(transaction));
	}

	@GetMapping
	public ResponseEntity<BigDecimal> getBalance() {
		return ok(transactionService.getBalance());
	}


}
