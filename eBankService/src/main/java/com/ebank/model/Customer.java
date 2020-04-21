package com.ebank.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cid;
	
	
	@NotEmpty(message = "Please provide a Customer Name")
	@Size(max = 20,min = 1)
	private String cname;
	
	@NotEmpty(message = "Please provide a Mobile Number")
	@Pattern(regexp="(^$|[0-9]{10})",message = "Provide valid Mobile Number")
	private String mobileno;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<BankAccount> bankaccount;

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public List<BankAccount> getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(List<BankAccount> bankaccount) {
		this.bankaccount = bankaccount;
	}
	
}
