package uy.maly.rewards.dtos;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class TransactionDTO {
	private Long transactionId;
	@NonNull
	private Long customerId;
	@NonNull
	private Date transactionDate;
	@NonNull
	private Float amount;
}
