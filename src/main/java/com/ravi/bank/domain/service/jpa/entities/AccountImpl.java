/**
 * 
 */
package com.ravi.bank.domain.service.jpa.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.security.auth.login.AccountLockedException;


/**
 * Represents an Account domain object.
 * 
 * Entity relationship:
 * 
 * An Account has:
 *	1 Customer (no joint accounts currently)
 * 
 * @author Ravi Aghera
 *
 */
@Entity
@Table(name="ACCOUNT")
public class AccountImpl implements Account {

	private Long accountId;
	private Double balance;
	private ACCOUNT_STATUS status;
	private ACCOUNT_TYPE accountType;
	private Integer currencyId;
	private Customer customer;
	
	private List<Transaction> transactionList;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="acc_seq")
	@SequenceGenerator(name="acc_seq", sequenceName="ACCOUNT_SEQ", allocationSize=1)
	@Column(name="ACCOUNT_ID")	
	@Override
	public Long getAccountId() {
		return accountId;
	}
	
	@Override
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	@Override
	//@Column(name="BALANCE")
	public Double getBalance() {
		return balance;
	}
	
	@Override
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	@Override
	@Enumerated(EnumType.STRING)
	//@Column(name="STATUS")
	public ACCOUNT_STATUS getStatus() {
		return status;
	}
	
	@Override
	public void setStatus(ACCOUNT_STATUS status) {
		this.status = status;
	}
	
	@Override
	@Enumerated(EnumType.STRING)
	@Column(name="ACCOUNT_TYPE")
	public ACCOUNT_TYPE getAccountType() {
		return accountType;
	}
	
	@Override
	public void setAccountType(ACCOUNT_TYPE type) {
		this.accountType = type;
	}
	
	@Override
	@Column(name="CURRENCY_ID")
	public Integer getCurrencyId() {
		return currencyId;
	}
	
	@Override
	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}
	
	//TargetEntity required since we are using interfaces
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=CustomerImpl.class)
	@JoinColumn(name="CUSTOMER_ID", referencedColumnName="CUSTOMER_ID")
	@Override
	public Customer getCustomer() {
		return customer;
	}
	
	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;
		
		//Manage the Entity relationship
//		if(null != customer.getAccountsList() && !customer.getAccountsList().contains(this)) {
//			customer.getAccountsList().add(this);
//		}
		
	}
	
	//Don't want to remove Transactions even if Customer is removed (better to archive them)
	//FetchType MUST be Lazy as there could be many transactions
	@OneToMany(mappedBy="account", orphanRemoval=false, fetch=FetchType.LAZY, targetEntity=TransactionImpl.class) 
	@Override
	public List<Transaction> getTransactionList() {
		return transactionList;
	}
	
	@Override
	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	/*
	 * TODO - See if there is a way of implementing these in Aspects
	 * based on the fields.  
	 * Or implement a ToStringService for instance to simplify this and do it 
	 * in one place.
	 * There is an Apache Commons lib that can do this via reflection.
	 */

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountImpl [accountId=");
		builder.append(accountId);
		builder.append(", balance=");
		builder.append(balance);
		builder.append(", status=");
		builder.append(status);
		builder.append(", accountType=");
		builder.append(accountType);
		builder.append(", currencyId=");
		builder.append(currencyId);
		builder.append(", customer=");
		builder.append(customer);
		//Only display the transactionId not the whole object as per Transaction#toString()
		if (null != transactionList && !transactionList.isEmpty()) {
			for (Transaction element : transactionList) {
				builder.append(", transaction=");
				builder.append(element.getTransactionId());
			}
		}
		builder.append("]");
		return builder.toString();
	}

	
	
}
