package com.ebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebank.model.TransactionHistory;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionHistory, Long> {

}
