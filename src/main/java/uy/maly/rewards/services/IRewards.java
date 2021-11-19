package uy.maly.rewards.services;

import java.util.List;

import uy.maly.rewards.dtos.CustomerRewardsDTO;
import uy.maly.rewards.dtos.RewardsDTO;

public interface IRewards {
	
	List<RewardsDTO> getRewardsForCostumer(Long customerId);
	List<CustomerRewardsDTO> getRewards();

}
