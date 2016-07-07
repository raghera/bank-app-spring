/**
 * 
 */
package com.ravi.bank.domain.service.ddo;

import java.util.Date;

import com.ravi.bank.domain.service.jpa.entities.Transaction;

/**
 * @author Ravi Aghera
 *
 */
public class TransactionSummaryImpl implements TransactionSummary {

	
	private Long transactionId;
	private Double amount;
	private Integer currency;
	private Integer transactionType;
	private Long accountId;
	private Date timestamp;
	
	public TransactionSummaryImpl(Transaction trans) {
		super();
		
		validateTransArg(trans);
		
		this.transactionId = trans.getTransactionId();
		this.amount = trans.getAmount();
		this.currency = trans.getCurrency();
		this.transactionType = trans.getTransactionType();
		this.accountId = trans.getAccount().getAccountId();
		this.timestamp = trans.getTimestamp();
	}

	//TODO put into an enclosed class.
	private boolean validateTransArg(Transaction trans) {
		
		if(null == trans || null == trans.getTransactionId()) {
			throw new IllegalArgumentException("Transaction cannot be null or not have an id when trying to create a TransactionSummary" );
		}
		
		if(trans == null || null == trans.getAccount() || null == trans.getAccount().getAccountId()) {
			throw new IllegalArgumentException("A Transaction MUST have an Account.  TransactionId does not: " + trans.getTransactionId());
		}
		
		return true;
		
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Integer getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	
	
}
