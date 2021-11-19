package uy.maly.rewards.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import uy.maly.rewards.dtos.TransactionDTO;
import uy.maly.rewards.exceptions.Rewards400Exception;
import uy.maly.rewards.exceptions.Rewards500Exception;
import uy.maly.rewards.services.ICustomer;
import uy.maly.rewards.services.ITransaction;

@RestController
public class TransactionsController implements TransactionsApi {

	private static final Logger log = LoggerFactory.getLogger(TransactionsController.class);

	private ITransaction iTransaction;

	public TransactionsController(ITransaction iTransaction) {
		this.iTransaction = iTransaction;
	}

	@Override
	public ResponseEntity<List<TransactionDTO>> getTransactions(Long customerId) {
		try {
			return new ResponseEntity<List<TransactionDTO>>(iTransaction.getTransactions(customerId), HttpStatus.OK);
		} catch (Rewards400Exception e) {
			log.info(e.getMessage());
			return new ResponseEntity<List<TransactionDTO>>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<List<TransactionDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<TransactionDTO> createTransactions(TransactionDTO dto) {
		try {
			return new ResponseEntity<TransactionDTO>(iTransaction.createTransaction(dto), HttpStatus.OK);

		} catch (Rewards400Exception e) {
			log.info(e.getMessage());
			return new ResponseEntity<TransactionDTO>(HttpStatus.NOT_FOUND);
		} catch (Rewards500Exception e) {
			log.info(e.getMessage());
			return new ResponseEntity<TransactionDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<TransactionDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<TransactionDTO> updateTransaction(Long transactionId, TransactionDTO dto) {
		try {
			return new ResponseEntity<TransactionDTO>(iTransaction.updateTransaction(transactionId, dto),
					HttpStatus.OK);
		} catch (Rewards400Exception e) {
			log.info(e.getMessage());
			return new ResponseEntity<TransactionDTO>(HttpStatus.NOT_FOUND);
		} catch (Rewards500Exception e) {
			log.info(e.getMessage());
			return new ResponseEntity<TransactionDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<TransactionDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
