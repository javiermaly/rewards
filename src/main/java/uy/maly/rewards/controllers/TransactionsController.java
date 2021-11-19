package uy.maly.rewards.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import uy.maly.rewards.dtos.TransactionDTO;
import uy.maly.rewards.exceptions.Rewards400Exception;
import uy.maly.rewards.services.ITransaction;

@RestController
public class TransactionsController implements TransactionsApi {
	
	private static final Logger log = LoggerFactory.getLogger(TransactionsController.class);

	@Autowired
	private ITransaction iTransaction;

	@Override
	public ResponseEntity<List<TransactionDTO>> getTransactions(Long customerId) {
		try {
			return new ResponseEntity<List<TransactionDTO>>(iTransaction.getTransactions(customerId), HttpStatus.OK);
		}catch(Rewards400Exception e) {
			log.info(e.getMessage());
			return new ResponseEntity<List<TransactionDTO>>(HttpStatus.NOT_FOUND);
		}
	}

}
