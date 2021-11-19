package uy.maly.rewards.dtos;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import uy.maly.rewards.dtos.CustomerDTO.CustomerDTOBuilder;

@Data
@Builder
public class TransactionDTO {
	@NonNull
	private Long customerId;
	@NonNull
	private Date transactionDate;
	@NonNull
	private Float amount;
}
