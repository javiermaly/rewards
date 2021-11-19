package uy.maly.rewards.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface RewardsApi {
	
    @RequestMapping(value = "/rewards/{customerId}", method = RequestMethod.GET)
	ResponseEntity<String> getRewards(@PathVariable("customerId") Long customerId);

}
