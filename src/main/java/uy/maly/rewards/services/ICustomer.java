package uy.maly.rewards.services;

import java.util.List;

import uy.maly.rewards.dtos.CustomerDTO;

public interface ICustomer {

	CustomerDTO getCustomer(Long customerId);
	List<CustomerDTO> getCustomers();
}
