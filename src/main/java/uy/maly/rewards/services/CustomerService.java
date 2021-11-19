package uy.maly.rewards.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.maly.rewards.dtos.CustomerDTO;
import uy.maly.rewards.exceptions.Rewards400Exception;
import uy.maly.rewards.models.Customer;
import uy.maly.rewards.repositories.CustomerRepository;

@Service
public class CustomerService implements ICustomer{
	
	private static final String CUSTOMER_NOT_FOUND = "Customer not found.";
	@Autowired
	private CustomerRepository iCustomerRepository;

	@Override
	public CustomerDTO getCustomer(Long customerId) {
		Optional<Customer> c = iCustomerRepository.findById(customerId);
		if(!c.isPresent()) {
			throw new Rewards400Exception(CUSTOMER_NOT_FOUND);
		}
		return CustomerDTO.builder().familyName(c.get().getFamilyName()).name(c.get().getName()).id(customerId).build();
	}

	@Override
	public List<CustomerDTO> getCustomers() {
		List<CustomerDTO> customers = new ArrayList<>();
		iCustomerRepository.findAll().forEach( x -> {
			customers.add(CustomerDTO.builder().familyName(x.getFamilyName()).name(x.getName()).id(x.getId()).build());
		});
		return customers;
	}

}
