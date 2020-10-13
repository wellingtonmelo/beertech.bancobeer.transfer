package beertech.becks.api.controller;

import beertech.becks.api.controllers.TransactionController;
import beertech.becks.api.entities.Balance;
import beertech.becks.api.entities.Transaction;
import beertech.becks.api.model.TypeOperation;
import beertech.becks.api.service.TransactionService;
import beertech.becks.api.tos.TransactionRequestTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

  @Mock private TransactionService transactionService;

  @InjectMocks private TransactionController transactionController;

  private MockMvc mockMvc;

  @BeforeEach
  void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
  }

  @Test
  void postValueInTransactionService() throws Exception {
    ObjectMapper mapper = new ObjectMapper();

    TransactionRequestTO transactionRequest = new TransactionRequestTO();
    transactionRequest.setOperation("D");
    transactionRequest.setValue(BigDecimal.valueOf(12));
    transactionRequest.setAccountDocument("123456");

    Transaction transaction = new Transaction();
    transaction.setId(1L);
    transaction.setTypeOperation(TypeOperation.DEPOSITO);
    transaction.setValueTransaction(BigDecimal.valueOf(12));

    MvcResult result =
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post("/bank/transactions")
                    .contentType(APPLICATION_JSON_VALUE)
                    .content(mapper.writeValueAsBytes(transactionRequest)))
            .andDo(print())
            .andExpect(status().isCreated())
            .andReturn();

    verify(transactionService, times(1)).processOperation(any(TransactionRequestTO.class));
  }

  @Test
  void getBalance() throws Exception {
    ObjectMapper mapper = new ObjectMapper();

    Balance balance = new Balance();
    balance.setBalance(BigDecimal.valueOf(123.5));
    when(transactionService.getBalance("")).thenReturn(balance);

    MvcResult result =
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.get("/bank/transactions").contentType(APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

    verify(transactionService, times(1)).getBalance("");

    String contentAsString = result.getResponse().getContentAsString();

    Balance balanceResponse = mapper.readValue(contentAsString, new TypeReference<Balance>() {});

    Assertions.assertEquals(balance.getBalance(), balanceResponse.getBalance());
  }
}
