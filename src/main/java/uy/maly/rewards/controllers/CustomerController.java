package uy.maly.rewards.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController implements ICustomerApi{

	@Override
	public ResponseEntity<String> getCustomer(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
