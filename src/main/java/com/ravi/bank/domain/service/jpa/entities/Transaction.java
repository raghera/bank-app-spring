/**
 * 
 */
package com.ravi.bank.domain.service.jpa.entities;

import java.util.Date;

/**
 * A Data Domain Object for the Transaction database table
 * 
 * @author Ravi Aghera
 *
 */
public interface Transaction {
	
	/**
	 * Getter
	 * @return - the transactionId field
	 */
	public Long getTransactionId();
	
	/**
	 * Setter
	 * @param transactionId - the value to be set
	 */
	public void setTransactionId(Long transactionId);
	
	/**
	 * Getter
	 * @return - the amount field
	 */
	public Double getAmount();
	
	/**
	 * Setter
	 * @param amount - the value to be set
	 */
	public void setAmount(Double amount);
	
	/**
	 * Getter
	 * @return - the currency field
	 */
	public Integer getCurrency();
	
	/**
	 * Setter
	 * @param currency - the value to be set
	 */
	public void setCurrency(Integer currency);
	
	/**
	 * Getter
	 * @return - the transactionType field
	 */
	public Integer getTransactionType();
	
	/**
	 * Setter
	 * @param transactionType - the value to be set
	 */
	public void setTransactionType(Integer transactionType);
	
	/**
	 * Getter
	 * @return - the timestamp field
	 */
	public Date getTimestamp();
	
	/**
	 * Setter
	 * @param timestamp - the value to be set
	 */
	public void setTimestamp(Date timestamp);
	
	/**
	 * Getter
	 * @return - the account field
	 */
	//public Customer getCustomer();
	public Account getAccount();

	

	
	/**
	 * Setter
	 * @param account - the value to be set
	 */
	//public void setCustomer(Customer customer);
	public void setAccount(Account account);
	
}
