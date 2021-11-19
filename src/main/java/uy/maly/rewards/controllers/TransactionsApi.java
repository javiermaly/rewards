package uy.maly.rewards.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uy.maly.rewards.dtos.TransactionDTO;

public interface TransactionsApi {
	
	@RequestMapping(value = "/transactions/{customerId}", method = RequestMethod.GET)
	ResponseEntity<List<TransactionDTO>> getTransactions(@PathVariable("customerId") Long customerId);

}
