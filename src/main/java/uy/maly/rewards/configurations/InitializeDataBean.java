//package uy.maly.rewards.configurations;
//
//import java.util.Date;
//import java.util.Random;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import uy.maly.rewards.models.Customer;
//import uy.maly.rewards.models.Transaction;
//import uy.maly.rewards.repositories.CustomerRepository;
//import uy.maly.rewards.repositories.TransactionRepository;
//
//@Component
//public class InitializeDataBean {
//
//	@Bean
//	public CommandLineRunner demo(TransactionRepository iTransactionRepository,
//			CustomerRepository iCustomerRepository) {
//		return (args) -> {
//			Customer customer1 = Customer.builder().familyName("Maly").name("Javier").username("jmaly").build();
//			Customer customer2 = Customer.builder().familyName("Doe").name("John").username("jdoe").build();
//			iCustomerRepository.save(customer1);
//			iCustomerRepository.save(customer2);	
//			for(int i=1; i<=20; i ++) {
//				if( i % 2 == 0) {
//					Transaction t = Transaction.builder().customer(customer1).transactionDate(new Date()).amount(getRandomFloat()).build();
//					iTransactionRepository.save(t);
//				}
//				else {
//					Transaction t = Transaction.builder().customer(customer2).transactionDate(new Date()).amount(getRandomFloat()).build();
//					iTransactionRepository.save(t);
//				}
//			}
//		};
//	}
//	
//	private float getRandomFloat() {
//		Random r = new Random();
//		float random = 1 + r.nextFloat() * (100 - 20);
//		return random;
//	}
//
//}
