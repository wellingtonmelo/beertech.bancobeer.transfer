package beertech.becks.api.entities;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
	@Id
	@ApiParam(value = "Transaction Id")
	private Long id;
	@ApiParam(value = "Operation to be performed (Depósito)")
	private String operation;
}
