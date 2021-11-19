package uy.maly.rewards.dtos;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRewardsDTO {
	private CustomerDTO customer;
	private int total;
	private List<RewardsDTO> rewards;

}
