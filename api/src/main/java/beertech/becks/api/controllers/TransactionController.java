package beertech.becks.api.controllers;

import beertech.becks.api.entities.Transaction;
import beertech.becks.api.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static beertech.becks.api.constants.Constants.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/transactions")
@Api(value = "Bank Becks Service")
public class TransactionController {

  @Autowired private TransactionService transactionService;

  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = STATUS_201_CREATED),
        @ApiResponse(code = 204, message = STATUS_204_NO_CONTENT),
        @ApiResponse(code = 400, message = STATUS_400_BAD_REQUEST)
      })
  @PostMapping
  public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
    return ok(transactionService.createTransaction(transaction));
  }

  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = STATUS_200_GET_OK),
        @ApiResponse(code = 400, message = STATUS_400_BAD_REQUEST),
        @ApiResponse(code = 500, message = STATUS_500_INTERNAL_SERVER_ERROR)
      })
  @GetMapping
  public ResponseEntity<BigDecimal> getBalance() {
    return ok(transactionService.getBalance());
  }
}
