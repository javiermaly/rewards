package uy.maly.rewards.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uy.maly.rewards.models.Customer;
import uy.maly.rewards.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	List<Transaction> findByCustomer(Customer c);

}
