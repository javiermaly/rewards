package uy.maly.rewards.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface ITransactionsApi {
	
	@RequestMapping(value = "/transactions/{customerId}")
	ResponseEntity<String> getTransactions(@PathVariable("customerId") Long customerId);

}
