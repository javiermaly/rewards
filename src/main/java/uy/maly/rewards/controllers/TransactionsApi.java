package uy.maly.rewards.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface TransactionsApi {
	
	@RequestMapping(value = "/transactions/{customerId}", method = RequestMethod.GET)
	ResponseEntity<String> getTransactions(@PathVariable("customerId") Long customerId);

}
