package uy.maly.rewards.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import uy.maly.rewards.dtos.CustomerDTO;
import uy.maly.rewards.dtos.CustomerRewardsDTO;
import uy.maly.rewards.dtos.RewardsDTO;
import uy.maly.rewards.services.ICustomer;
import uy.maly.rewards.services.IRewards;

@RestController
public class RewardsController implements RewardsApi{
	
	private static final Logger log = LoggerFactory.getLogger(RewardsController.class);
	
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
