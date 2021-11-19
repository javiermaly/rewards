package uy.maly.rewards.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import uy.maly.rewards.dtos.CustomerDTO;
import uy.maly.rewards.exceptions.Rewards400Exception;
import uy.maly.rewards.services.ICustomer;

@RestController
public class CustomerController implements CustomerApi {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	private ICustomer iCustomer;

	public CustomerController(ICustomer iCustomer) {
		this.iCustomer = iCustomer;
	}

	@Override
	public ResponseEntity<CustomerDTO> getCustomer(Long customerId) {
		try {
			return new ResponseEntity<CustomerDTO>(iCustomer.getCustomer(customerId), HttpStatus.OK);

		} catch (Rewards400Exception e) {
			log.info(e.getMessage());
			return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<CustomerDTO>> getCustomers() {
		return new ResponseEntity<List<CustomerDTO>>(iCustomer.getCustomers(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CustomerDTO> createCustomer(CustomerDTO dto) {
		try {
			return new ResponseEntity<CustomerDTO>(iCustomer.createCustomer(dto), HttpStatus.OK);

		} catch (Rewards400Exception e) {
			log.info(e.getMessage());
			return new ResponseEntity<CustomerDTO>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info(e.getMessage());
			return new ResponseEntity<CustomerDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
