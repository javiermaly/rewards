package uy.maly.rewards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uy.maly.rewards.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
