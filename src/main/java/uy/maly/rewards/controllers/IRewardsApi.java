package uy.maly.rewards.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface IRewardsApi {
	
    @RequestMapping(value = "/rewards/{customerId}")
	ResponseEntity<String> getRewards(@PathVariable("customerId") Long customerId);

}
