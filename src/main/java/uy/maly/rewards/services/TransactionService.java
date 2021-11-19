package uy.maly.rewards.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

	private static final String CUSTOMER_NOT_FOUND = "Customer not found.";
	private static final String TRANSACTION_NOT_FOUND = "Transaction not found.";

	private TransactionRepository transactionRepository;

	private CustomerRepository customerRepository;

	public TransactionService(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		this.transactionRepository = transactionRepository;
	}

	@Override
	public List<TransactionDTO> getTransactions(Long customerId) {
		log.info("getTransactions" + customerId);
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
		log.info("createTransaction" + dto.toString());
		Optional<Customer> customer = customerRepository.findById(dto.getCustomerId());
		if (!customer.isPresent()) {
			throw new Rewards400Exception(CUSTOMER_NOT_FOUND);
		}
		try {
			Transaction t = transactionRepository.save(Transaction.builder().amount(dto.getAmount())
					.customer(customer.get()).transactionDate(dto.getTransactionDate()).build());
			return buildTransactionDTOFromTransaction(t);
		} catch (Exception e) {
			throw new Rewards500Exception("Error saving transaction: " + e.getMessage());
		}
	}

	@Override
	public TransactionDTO updateTransaction(Long transactionId, TransactionDTO dto) {
		log.info("updateTransaction: " + dto.toString() + " transactionId: " + transactionId);
		Optional<Transaction> tr = transactionRepository.findById(transactionId);
		Transaction t;
		if (!tr.isPresent()) {
			throw new Rewards400Exception(TRANSACTION_NOT_FOUND);
		}
		Optional<Customer> customer = customerRepository.findById(dto.getCustomerId());
		if (!customer.isPresent()) {
			throw new Rewards400Exception(CUSTOMER_NOT_FOUND);
		}
		t = tr.get();
		t.setAmount(dto.getAmount());
		t.setTransactionDate(dto.getTransactionDate());
		t.setCustomer(customer.get());
		t = transactionRepository.save(t);
		return buildTransactionDTOFromTransaction(t);
	}

	private TransactionDTO buildTransactionDTOFromTransaction(Transaction t) {
		return TransactionDTO.builder().transactionId(t.getId()).amount(t.getAmount())
				.customerId(t.getCustomer().getId()).transactionDate(t.getTransactionDate()).build();
	}

}
