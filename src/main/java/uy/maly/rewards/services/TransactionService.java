package uy.maly.rewards.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import uy.maly.rewards.dtos.TransactionDTO;
import uy.maly.rewards.exceptions.Rewards400Exception;
import uy.maly.rewards.exceptions.Rewards500Exception;
import uy.maly.rewards.models.Customer;
import uy.maly.rewards.models.Transaction;
import uy.maly.rewards.repositories.CustomerRepository;
import uy.maly.rewards.repositories.TransactionRepository;

@Service
public class TransactionService implements ITransaction {

	private static final String CUSTOMER_NOT_FOUND = "Customer not found.";

	private TransactionRepository transactionRepository;

	private CustomerRepository customerRepository;

	public TransactionService(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		this.transactionRepository = transactionRepository;
	}

	@Override
	public List<TransactionDTO> getTransactions(Long customerId) {
		List<TransactionDTO> transactions = new ArrayList<TransactionDTO>();
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (!customer.isPresent()) {
			throw new Rewards400Exception(CUSTOMER_NOT_FOUND);
		}
		transactionRepository.findByCustomer(customer.get()).forEach(x -> {
			transactions.add(buildTransactionDTOFromTransaction(x));
		});
		return transactions;
	}

	@Override
	public TransactionDTO createTransaction(TransactionDTO dto) {
		Optional<Customer> customer = customerRepository.findById(dto.getCustomerId());
		if (!customer.isPresent()) {
			throw new Rewards400Exception(CUSTOMER_NOT_FOUND);
		}
		try {
			Transaction t = transactionRepository.save(Transaction.builder().amount(dto.getAmount()).customer(customer.get()).transactionDate(dto.getTransactionDate()).build());
			return buildTransactionDTOFromTransaction(t);
		}catch(Exception e) {
			throw new Rewards500Exception("Error saving transaction: " + e.getMessage());
		}
	}

	private TransactionDTO buildTransactionDTOFromTransaction(Transaction t) {
		return TransactionDTO.builder().amount(t.getAmount()).customerId(t.getCustomer().getId())
				.transactionDate(t.getTransactionDate()).build();
	}

}
