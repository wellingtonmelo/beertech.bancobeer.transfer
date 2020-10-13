package beertech.becks.api.tos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestTO {
  /** The operation type (S || D) */
  private String operation;

  /** The operation value */
  private BigDecimal value;

  @NotBlank private String accountDocument;

  private String fromAccountDocument;

  private String toAccountDocument;
}
