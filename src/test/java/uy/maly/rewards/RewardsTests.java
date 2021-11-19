package uy.maly.rewards;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import uy.maly.rewards.controllers.CustomerApi;
import uy.maly.rewards.controllers.TransactionsApi;
import uy.maly.rewards.dtos.CustomerDTO;
import uy.maly.rewards.dtos.TransactionDTO;
import uy.maly.rewards.models.Customer;
import uy.maly.rewards.repositories.CustomerRepository;

@SpringBootTest
@Transactional
public class RewardsTests {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerApi customerApi;

	@Autowired
	TransactionsApi transactionsApi;

	@Test
	void createCustomerTest() {
		customerRepository.save(Customer.builder().familyName("John").name("Smith").username("jsmith").build());
		Optional<Customer> customer = customerRepository.findByUsername("jsmith");
		assert (customer.isPresent());
	}

	@Test
	void getCustomer() {
		assert (customerApi.getCustomer(2L).getStatusCode().is2xxSuccessful());
	}

	@Test
	void getCustomers() {
		assert (customerApi.getCustomers().getBody().size() > 0);
	}

	@Test
	void createCustomer() {
		assert (customerApi.createCustomer(CustomerDTO.builder().familyName("John").name("John").username("jj").build())
				.getBody().getId() != 0);
	}

	@Test
	void getTransactions() {
		assert (transactionsApi.getTransactions(2L).getBody().size() > 0);
	}

	@Test
	void createTransactions() {
		assert (transactionsApi.createTransactions(
				TransactionDTO.builder().amount(new Float("2.3")).customerId(2L).transactionDate(new Date()).build())
				.getBody().getTransactionId() != 0);
	}

	@Test
	void updateTransaction() {
		assert (transactionsApi.updateTransaction(2L,
				TransactionDTO.builder().amount(new Float("55")).transactionDate(new Date()).customerId(2L).build())
				.getStatusCode().is2xxSuccessful());
	}

}
