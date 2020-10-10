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

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/transactions")
@Api(value = "Bank Becks Service")
public class TransactionController {
  public static final String STATUS_200_GET_OK = "Successfully retrieved";
  public static final String STATUS_201_CREATED = "Successfully created";
  public static final String STATUS_204_NO_CONTENT = "No Content";
  public static final String STATUS_400_BAD_REQUEST = "Resource is invalid";
  public static final String STATUS_500_INTERNAL_SERVER_ERROR =
      "The application has encountered an unknown error. Please try again.";

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
