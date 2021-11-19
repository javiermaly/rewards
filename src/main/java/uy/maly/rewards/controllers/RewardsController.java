package uy.maly.rewards.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import uy.maly.rewards.dtos.CustomerRewardsDTO;
import uy.maly.rewards.dtos.RewardsDTO;
import uy.maly.rewards.services.IRewards;

@RestController
public class RewardsController implements RewardsApi{
		
	private IRewards iRewards;
	
	public RewardsController(IRewards iRewards) {
		this.iRewards = iRewards;
	}

	@Override
	public ResponseEntity<List<RewardsDTO>> getRewardsForCostumer(Long customerId) {
		return new ResponseEntity<List<RewardsDTO>>(iRewards.getRewardsForCostumer(customerId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<CustomerRewardsDTO>> getRewards() {
		return new ResponseEntity<List<CustomerRewardsDTO>>(iRewards.getRewards(), HttpStatus.OK);
	}
	

	



}
