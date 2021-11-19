package uy.maly.rewards.services;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import uy.maly.rewards.dtos.CustomerRewardsDTO;
import uy.maly.rewards.dtos.RewardsDTO;
import uy.maly.rewards.exceptions.Rewards400Exception;
import uy.maly.rewards.models.Customer;
import uy.maly.rewards.models.Transaction;
import uy.maly.rewards.repositories.CustomerRepository;
import uy.maly.rewards.repositories.TransactionRepository;

@Service
public class RewardsService implements IRewards {

	private static final Logger log = LoggerFactory.getLogger(RewardsService.class);

	private static final String CUSTOMER_NOT_FOUND = "Customer not found.";

	private CustomerRepository customerRepository;

	private TransactionRepository transactionRepository;

	public RewardsService(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
		this.customerRepository = customerRepository;
		this.transactionRepository = transactionRepository;
	}

	@Override
	public List<RewardsDTO> getRewardsForCostumer(Long customerId) {
		
		List<RewardsDTO> rewards = new ArrayList<RewardsDTO>();
		
		Optional<Customer> c = customerRepository.findById(customerId);
		if (!c.isPresent()) {
			throw new Rewards400Exception(CUSTOMER_NOT_FOUND);
		}
		
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.MONTH, -3);
		
		List<Transaction> transactions = transactionRepository.findByCustomerAndDate(c.get(), cal.getTime());
		
		transactions.forEach(x->{
			rewards.add(RewardsDTO.builder().month(x.getTransactionDate().getMonth()).points(33).build());
		});
		return rewards;
		
	}

	@Override
	public List<CustomerRewardsDTO> getRewards() {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getPoints(List<Transaction> transactions) {
		Integer points = 33;
		return points;
	}

}
