package beertech.becks.api.controllers;

import beertech.becks.api.entities.Transaction;
import beertech.becks.api.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionRepository repository;

	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
		transaction.setId(1L);
		Transaction persistedTransaction = repository.save(transaction);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(persistedTransaction.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
