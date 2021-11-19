package uy.maly.rewards.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uy.maly.rewards.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Optional<Customer> findByUsername(String username);

}
