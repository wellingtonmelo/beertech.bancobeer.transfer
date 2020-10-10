package beertech.becks.api.controllers;

import static beertech.becks.api.constants.Constants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import beertech.becks.api.entities.Balance;
import beertech.becks.api.entities.Transaction;
import beertech.becks.api.service.TransactionService;
import beertech.becks.api.tos.TransactionRequestTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/transactions")
@Api(value = "Bank Becks Service")
public class TransactionController {

  @Autowired private TransactionService transactionService;

  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = STATUS_201_CREATED),
        @ApiResponse(code = 400, message = STATUS_400_BAD_REQUEST),
        @ApiResponse(code = 500, message = STATUS_500_INTERNAL_SERVER_ERROR)
      })
	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequestTO transactionTO) {
		Transaction createdTransaction = transactionService.createTransaction(transactionTO);

		return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
	}

  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = STATUS_200_GET_OK),
        @ApiResponse(code = 404, message = STATUS_404_NOTFOUND),
        @ApiResponse(code = 500, message = STATUS_500_INTERNAL_SERVER_ERROR)
      })
  @GetMapping
  public ResponseEntity<Balance> getBalance() {
    Balance balance = transactionService.getBalance();
    return new ResponseEntity<>(balance, HttpStatus.OK);
  }

}
