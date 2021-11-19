package uy.maly.rewards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uy.maly.rewards.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
