package uy.maly.rewards.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface ICustomerApi {
	
	@RequestMapping(value = "/customers/{customerId}")
	ResponseEntity<String> getCustomer(@PathVariable("customerId") Long customerId);

}
