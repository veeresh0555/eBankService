package com.ebank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ebank.model.Customer;
import com.ebank.model.TransactionHistory;
import com.ebank.service.BankService;

@RestController
@RequestMapping("/ebank")
public class BankController {

	@Autowired
	BankService bservice;
	
	@Autowired
	Environment env;

	@GetMapping("/info")
	public String info() {
		String port =env.getProperty("local.server.port");
		return "eBankService running port is: "+port;
	}
	
	@PostMapping
	public ResponseEntity<?> saveOrUpdateCustomer(@Valid Customer customer){
		Customer updatecust= bservice.saveOrUpdateCustomer(customer);
		return new ResponseEntity<>(updatecust,new HttpHeaders(), HttpStatus.OK);
	}
	@PostMapping("/payment")
	public ResponseEntity<TransactionHistory> paymentofOrder(@RequestBody TransactionHistory transhistory) throws Exception{
		TransactionHistory transdata=bservice.makepayment(transhistory);
		return new ResponseEntity<>(transdata,new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/lateststmt")
	public ResponseEntity<List<TransactionHistory>> latest10records(@RequestParam("userid") long userid){
		List<TransactionHistory> top10records=bservice.getstatement(userid);
		return new ResponseEntity<List<TransactionHistory>>(top10records,new HttpHeaders(),HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
}
