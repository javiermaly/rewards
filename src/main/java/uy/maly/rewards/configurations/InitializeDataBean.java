package uy.maly.rewards.configurations;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import uy.maly.rewards.models.Customer;
import uy.maly.rewards.models.Transaction;
import uy.maly.rewards.repositories.CustomerRepository;
import uy.maly.rewards.repositories.TransactionRepository;

@Component
public class InitializeDataBean {

	private static final int TRANSACTIONS_NUMBER = 20;

	@Bean
	public CommandLineRunner demo(TransactionRepository iTransactionRepository,
			CustomerRepository iCustomerRepository) {
		return (args) -> {
			Customer customer1 = Customer.builder().familyName("Maly").name("Javier").username("jmaly").build();
			Customer customer2 = Customer.builder().familyName("Doe").name("John").username("jdoe").build();
			iCustomerRepository.save(customer1);
			iCustomerRepository.save(customer2);
			for (int i = 1; i <= TRANSACTIONS_NUMBER; i++) {
				if (i % 2 == 0) {
					Transaction t = Transaction.builder().customer(customer1).transactionDate(getRandomDate())
							.amount(getRandomFloat()).build();
					iTransactionRepository.save(t);
				} else {
					Transaction t = Transaction.builder().customer(customer2).transactionDate(getRandomDate())
							.amount(getRandomFloat()).build();
					iTransactionRepository.save(t);
				}
			}
		};
	}

	private float getRandomFloat() {
		Random r = new Random();
		float random = 1 + r.nextFloat() * (100 - 20);
		return random;
	}

	private Date getRandomDate() {
		Random rand = new Random();
		List<LocalDate> dates = getDatesBetween();
		LocalDate randomDate = dates.get(rand.nextInt(dates.size()));
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(randomDate.atStartOfDay(defaultZoneId).toInstant());
		return date;
	}

	private List<LocalDate> getDatesBetween() {
		LocalDate endDate = LocalDate.now();
		LocalDate startDate = endDate.minusMonths(3);
		long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);
		List<LocalDate> listOfDates = Stream.iterate(startDate, date -> date.plusDays(1)).limit(numOfDays)
				.collect(Collectors.toList());
		System.out.println(listOfDates.size()); // 61
		return listOfDates;
	}

}
