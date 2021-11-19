package uy.maly.rewards.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uy.maly.rewards.dtos.TransactionDTO;

public interface TransactionsApi {
	
	@RequestMapping(value = "/transactions/{customerId}", method = RequestMethod.GET)
	ResponseEntity<List<TransactionDTO>> getTransactions(@PathVariable("customerId") Long customerId);
	
	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	ResponseEntity<TransactionDTO> createTransactions(@RequestBody TransactionDTO dto);
	
	@RequestMapping(value = "/transactions/{transactionId}", method = RequestMethod.PATCH)
	ResponseEntity<TransactionDTO> updateTransaction(@PathVariable("transactionId") Long transactionId, @RequestBody TransactionDTO dt);

}
