package uy.maly.rewards.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import uy.maly.rewards.dtos.CustomerDTO;
import uy.maly.rewards.dtos.CustomerRewardsDTO;
import uy.maly.rewards.dtos.RewardsDTO;
import uy.maly.rewards.exceptions.Rewards400Exception;
import uy.maly.rewards.exceptions.Rewards500Exception;
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
		Optional<Customer> c = customerRepository.findById(customerId);
		if (!c.isPresent()) {
			throw new Rewards400Exception(CUSTOMER_NOT_FOUND);
		}
		try {
			return rewardsByCustomer(c.get());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Rewards500Exception(e.getMessage());
		}

	}

	@Override
	public List<CustomerRewardsDTO> getRewards() {
		try {
			List<Customer> customers = customerRepository.findAll();
			List<CustomerRewardsDTO> rewards = new ArrayList<CustomerRewardsDTO>();
			customers.forEach(x -> {
				CustomerDTO cus = CustomerDTO.builder().familyName(x.getFamilyName()).id(x.getId()).name(x.getName())
						.username(x.getUsername()).build();
				List<RewardsDTO> rew = rewardsByCustomer(x);
				int total = rew.stream().map(RewardsDTO::getPoints).reduce(0, (o, p) -> p + o);
				rewards.add(CustomerRewardsDTO.builder().customer(cus).rewards(rew).total(total).build());

			});
			return rewards;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Rewards500Exception(e.getMessage());
		}
	}

	private List<RewardsDTO> rewardsByCustomer(Customer c) {
		List<RewardsDTO> rewards = new ArrayList<RewardsDTO>();
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.MONTH, -3);
		List<Transaction> transactions = transactionRepository.findByCustomerAndDate(c, cal.getTime());
		// Grouping Transactions by month
		Map<Integer, List<Transaction>> transactionsPerMonth = transactions.stream()
				.collect(Collectors.groupingBy(Transaction::getMonth));
		for (Integer i : transactionsPerMonth.keySet()) {
			int res = transactionsPerMonth.get(i).stream().map(Transaction::getScore).reduce(0, (t, c1) -> c1 + t);
			rewards.add(RewardsDTO.builder().month(i).points(res).build());
		}
		return rewards;
	}

}
