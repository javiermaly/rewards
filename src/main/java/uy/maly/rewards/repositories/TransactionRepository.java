package uy.maly.rewards.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uy.maly.rewards.models.Customer;
import uy.maly.rewards.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	List<Transaction> findByCustomer(Customer c);
	
	@Query("SELECT t FROM Transaction t WHERE t.customer=?1 AND t.transactionDate > ?2 ORDER BY t.transactionDate")
	List<Transaction> findByCustomerAndDate(Customer customer, Date date);

}
