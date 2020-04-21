package com.ebank.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebank.model.Customer;
import com.ebank.model.TransactionHistory;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<TransactionHistory> findstmtById(long userid, Pageable top10);

}
