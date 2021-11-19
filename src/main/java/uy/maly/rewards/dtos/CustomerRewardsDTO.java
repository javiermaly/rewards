package uy.maly.rewards.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRewardsDTO {
	private CustomerDTO customer;
	private RewardsDTO rewards;

}
