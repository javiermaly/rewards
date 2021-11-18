package uy.maly.rewards.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionsController implements ITransactionsApi {

	@Override
	public ResponseEntity<String> getTransactions(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
