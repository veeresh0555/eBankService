package com.ebank.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ebank.exception.RecordsNotFoundException;
import com.ebank.model.BankAccount;
import com.ebank.model.Customer;
import com.ebank.model.TransactionHistory;
import com.ebank.repository.BankRepository;
import com.ebank.repository.CustomerRepository;
import com.ebank.repository.TransactionRepository;

@Service
@Transactional
public class BankServiceImpl implements BankService {

	@Autowired
	CustomerRepository crepository;
	
	@Autowired
	BankRepository brepository;
	
	@Autowired
	TransactionRepository transrepo;

	@Override
	public Customer saveOrUpdateCustomer(Customer customer) {
		Optional<Customer> findBycustomer=crepository.findById(customer.getCid());
		if(findBycustomer.isPresent()){
			Customer updatecust=findBycustomer.get();
			updatecust.setCid(customer.getCid());
			updatecust.setCname(customer.getCname());
			updatecust.setMobileno(customer.getMobileno());
			List<BankAccount> baccounts=updatecust.getBankaccount();
			baccounts.stream().forEach(uacb->uacb.setBalance(10000.00));
			baccounts.stream().forEach(baccount->baccount.setCustomer(updatecust));
			baccounts.forEach(System.out::println);
			updatecust.setBankaccount(baccounts);
			return crepository.save(updatecust);
		}else {
			System.out.println("This is Else block");
			List<BankAccount> cbaccount=customer.getBankaccount();
			cbaccount.stream().forEach(acb->acb.setBalance(10000.00));
			cbaccount.stream().forEach(baccount->baccount.setCustomer(customer));
			cbaccount.forEach(System.out::println);
			customer.setBankaccount(cbaccount);
			return crepository.save(customer);
		}
	}

	@Override
	public TransactionHistory makepayment(long cid,Double amount) throws Exception {

		System.out.println("Enter BankServiceImpl->makePament method: ");
		System.out.println("Customer id: "+cid+"\t amount: "+amount);
		TransactionHistory transhistory=new TransactionHistory();
		Optional<Customer> checkcustById=crepository.findById(cid);
		BankAccount baccount=new BankAccount();
		if(checkcustById.isPresent()) {
			Customer cprasent=checkcustById.get();
			List<BankAccount> bankacc=cprasent.getBankaccount();
			//bankacc.stream().forEach(blst->baccount.setBalance(blst.getBalance()));
			System.out.println("condition: "+(baccount.getBalance() >= amount));
			
			if(baccount.getBalance() >= amount) {
				System.out.println("");
				bankacc.stream().forEach(updatelist->baccount.setBalance((updatelist.getBalance())-(amount)));
				bankacc.stream().forEach(updatecardno->baccount.setCardnumber(updatecardno.getCardnumber()));
				cprasent.setBankaccount(bankacc);
				bankacc.forEach(lst->System.out.println("Balance: "+lst.getBalance()+"\t card Number "+lst.getCardnumber()));
				crepository.save(cprasent);
				transhistory.setUserid(cprasent.getCid());
				transhistory.setCardno(baccount.getCardnumber());
				transhistory.setAmount(amount);
				return transrepo.save(transhistory);
			}else {
				throw new RecordsNotFoundException("Insufficient balance");
			}
		}else {
			throw new RecordsNotFoundException("Customer Not available");
		}
	}

	@Override
	public List<TransactionHistory> getstatement(long userid) {
		Pageable top10=PageRequest.of(0, 10);
		List<TransactionHistory> stmt=  transrepo.findstmtById(userid, top10);
		return stmt;
	}
	
	
}
