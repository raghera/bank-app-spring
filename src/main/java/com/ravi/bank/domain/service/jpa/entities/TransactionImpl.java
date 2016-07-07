/**
 * 
 */
package com.ravi.bank.domain.service.jpa.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * A Data Domain Object for the transaction database table
 * 
 * @author Ravi Aghera
 *
 */
@Entity
@Table(name="TRANSACTION")
public class TransactionImpl implements Transaction {
	
	private Long transactionId;
	private Double amount;
	private Integer currency;
	private Integer transactionType;
	private Date timestamp;
	private Account account;
	
	/*
	 * If timestamp has not been populated explicitly
	 * it will be populated here since it cannot be null
	 */
	@PrePersist
	private void populateTimeStamp() {
		if(timestamp == null) {
			timestamp = new Date();
		}
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="trans_seq")
	@SequenceGenerator(name="trans_seq", sequenceName="TRANSACTION_SEQ", allocationSize=1, initialValue=1)
	@Column(name="TRANSACTION_ID")
	@Override
	public Long getTransactionId() {
		return transactionId;
	}
	
	@Override
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	
	@Override
	public Double getAmount() {
		return amount;
	}
	
	@Override
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Override
	public Integer getCurrency() {
		return currency;
	}
	
	@Override
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	
	@Column(name="TRANSACTION_TYPE")
	@Override
	public Integer getTransactionType() {
		return transactionType;
	}
	
	@Override
	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}
	
	@Override
	@Basic(optional=false)
	//@Column(name="TIMESTAMP", insertable = false, updatable = false ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" 
	//ON UPDATE CURRENT_TIMESTAMP"
	//)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTimestamp() {
		return timestamp;
	}
	
	@Override
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	//Transaction must have at least 1 account
	//Since using interfaces we need to put the targetEntity class (Otherwise could have been inferred as Account)
	@ManyToOne(fetch=FetchType.LAZY, optional=false, targetEntity=AccountImpl.class)
	@JoinColumn(name="ACCOUNT_ID")
	@Override
	public Account getAccount() {
		return this.account;
		
	}

	
	@Override
	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TransactionImpl [transactionId=");
		builder.append(transactionId);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", transactionType=");
		builder.append(transactionType);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append(", account=");
		builder.append(account);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((currency == null) ? 0 : currency.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result
				+ ((transactionId == null) ? 0 : transactionId.hashCode());
		result = prime * result
				+ ((transactionType == null) ? 0 : transactionType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionImpl other = (TransactionImpl) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}

	

	
	
	
}
