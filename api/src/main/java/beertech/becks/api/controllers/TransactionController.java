package beertech.becks.api.controllers;

import beertech.becks.api.entities.Transaction;
import beertech.becks.api.repositories.TransactionRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Api(tags = {"Bank Beer Service"})
@RestController
@RequestMapping("/transactions")
public class TransactionController {

  public static final String STATUS_200_GET_OK = "Successfully retrieved";
  public static final String STATUS_201_CREATED = "Successfully created";
  public static final String STATUS_204_NO_CONTENT = "No Content";
  public static final String STATUS_400_BAD_REQUEST = "Resource is invalid";
  public static final String STATUS_500_INTERNAL_SERVER_ERROR =
      "The application has encountered an unknown error. Please try again.";

  @Autowired private TransactionRepository repository;

  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = STATUS_201_CREATED),
        @ApiResponse(code = 204, message = STATUS_204_NO_CONTENT),
        @ApiResponse(code = 400, message = STATUS_400_BAD_REQUEST)
      })
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
    transaction.setId(1L);
    Transaction persistedTransaction = repository.save(transaction);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(persistedTransaction.getId())
            .toUri();

    return ResponseEntity.created(location).build();
  }
}
