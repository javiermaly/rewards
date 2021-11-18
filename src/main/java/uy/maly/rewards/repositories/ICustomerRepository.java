package uy.maly.rewards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uy.maly.rewards.models.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Long>{

}
