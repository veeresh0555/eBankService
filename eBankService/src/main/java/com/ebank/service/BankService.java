package com.ebank.service;

import java.util.List;

import com.ebank.model.Customer;
import com.ebank.model.TransactionHistory;

public interface BankService {

	public Customer saveOrUpdateCustomer(Customer customer);

	public TransactionHistory makepayment(TransactionHistory transhistory) throws Exception;

	public List<TransactionHistory> getstatement(long userid);

}
