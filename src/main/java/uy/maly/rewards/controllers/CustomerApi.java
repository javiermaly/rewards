package uy.maly.rewards.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uy.maly.rewards.dtos.CustomerDTO;

public interface CustomerApi {
	
	@RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
	ResponseEntity<CustomerDTO> getCustomer(@PathVariable("customerId") Long customerId);
	
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	ResponseEntity<List<CustomerDTO>> getCustomers();
	
	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO dto);

}
