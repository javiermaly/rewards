package uy.maly.rewards.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uy.maly.rewards.dtos.CustomerRewardsDTO;
import uy.maly.rewards.dtos.RewardsDTO;

public interface RewardsApi {
	
    @RequestMapping(value = "/rewards/{customerId}", method = RequestMethod.GET)
	ResponseEntity<List<RewardsDTO>> getRewardsForCostumer(@PathVariable("customerId") Long customerId);
    
    @RequestMapping(value = "/rewards", method = RequestMethod.GET)
	ResponseEntity<List<CustomerRewardsDTO>> getRewards();
    

}
