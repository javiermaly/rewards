package uy.maly.rewards.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import uy.maly.rewards.controllers.RewardsController;
import uy.maly.rewards.dtos.CustomerDTO;
import uy.maly.rewards.dtos.TransactionDTO;
import uy.maly.rewards.exceptions.Rewards400Exception;
import uy.maly.rewards.models.Customer;
import uy.maly.rewards.models.Transaction;
import uy.maly.rewards.repositories.CustomerRepository;

@Service
public class CustomerService implements ICustomer {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);


	private static final String EXISTENT_CUSTOMER = "Existent customer.";

	private static final String CUSTOMER_NOT_FOUND = "Customer not found.";

	private CustomerRepository iCustomerRepository;

	public CustomerService(CustomerRepository iCustomerRepository) {
		this.iCustomerRepository = iCustomerRepository;
	}

	@Override
	public CustomerDTO getCustomer(Long customerId) {
		Optional<Customer> c = iCustomerRepository.findById(customerId);
		if (!c.isPresent()) {
			throw new Rewards400Exception(CUSTOMER_NOT_FOUND);
		}
		return CustomerDTO.builder().familyName(c.get().getFamilyName()).name(c.get().getName()).id(c.get().getId())
				.username(c.get().getUsername()).build();
	}

	@Override
	public List<CustomerDTO> getCustomers() {
		List<CustomerDTO> customers = new ArrayList<>();
		iCustomerRepository.findAll().forEach(x -> {
			customers.add(buildCustomerDTOFromCustomer(x));
		});
		return customers;
	}

	@Override
	public CustomerDTO createCustomer(CustomerDTO dto) {
		Optional<Customer> c = iCustomerRepository.findByUsername(dto.getUsername());
		Customer cus;
		if (c.isPresent()) {
			throw new Rewards400Exception(EXISTENT_CUSTOMER);
		}
		cus = iCustomerRepository.save(Customer.builder().familyName(dto.getFamilyName()).name(dto.getName())
				.username(dto.getUsername()).build());
		return buildCustomerDTOFromCustomer(cus);

	}

	private CustomerDTO buildCustomerDTOFromCustomer(Customer c) {
		return CustomerDTO.builder().familyName(c.getFamilyName()).name(c.getName()).id(c.getId())
				.username(c.getUsername()).build();
	}
}
