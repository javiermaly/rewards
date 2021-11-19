package uy.maly.rewards.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RewardsController implements RewardsApi{
	
	private static final Logger log = LoggerFactory.getLogger(RewardsController.class);

	@Override
	public ResponseEntity<String> getRewards(Long customerId) {
		
		log.info(customerId.toString());
		
		return new ResponseEntity<>("hello world", HttpStatus.OK);

	}


}
