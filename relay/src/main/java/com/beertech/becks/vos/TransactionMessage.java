package com.beertech.becks.vos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransactionMessage {

  @NotBlank private String fromAccountDocument;

  @NotBlank private String toAccountDocument;

  @NotNull private BigDecimal value;

  @NotBlank private String operation;

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public String getFromAccountDocument() {
    return fromAccountDocument;
  }

  public void setFromAccountDocument(String fromAccountDocument) {
    this.fromAccountDocument = fromAccountDocument;
  }

  public String getToAccountDocument() {
    return toAccountDocument;
  }

  public void setToAccountDocument(String toAccountDocument) {
    this.toAccountDocument = toAccountDocument;
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }
}
