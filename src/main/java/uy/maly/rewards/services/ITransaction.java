package uy.maly.rewards.services;

import java.util.List;

import uy.maly.rewards.dtos.TransactionDTO;

public interface ITransaction {

	List<TransactionDTO> getTransactions(Long customerId);
	
	TransactionDTO createTransaction(TransactionDTO dto);
	
	TransactionDTO updateTransaction(Long transactionId, TransactionDTO dto);
}
