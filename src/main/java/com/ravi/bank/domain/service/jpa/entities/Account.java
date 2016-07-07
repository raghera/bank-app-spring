package com.ravi.bank.domain.service.jpa.entities;

import java.util.List;

/**
 * A Data Domain Object for the Account database table
 * @author Ravi Aghera
 *
 */
public interface Account {
	
	public enum ACCOUNT_STATUS {

		OPEN(101, "OPEN"),
		CLOSED(102, "CLOSED");
		
		private final int id;
		private String	name;
		
		private ACCOUNT_STATUS(int id, String name){
			this.id = id;
			this.name = name;
		}
		
		public int getId() {
			return id;
		}
		public String getName() {
			return name;
		}
	}
	
	public enum ACCOUNT_TYPE {
		
		CURRENT(101, "CURRENT"),
		SAVINGS(102, "SAVINGS");
		
		private int id;
		private String name;
		
		private ACCOUNT_TYPE(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public int getId() {
			return id;
		}
		
		public String getName() {
			return name;
		}
		
	}
	
	/**
	 * Getter
	 * @return - the accountId field
	 */
	public Long getAccountId();
	
	/**
	 * Setter
	 * @param accountId - the value to be set
	 */
	public void setAccountId(Long accountId);
	
	/**
	 * Getter
	 * @return - the balance field
	 */
	public Double getBalance();
	
	/**
	 * Setter
	 * @param balance - the value to be set
	 */
	public void setBalance(Double balance);
	
	/**
	 * Getter
	 * @return - the status field
	 */
	public ACCOUNT_STATUS getStatus();
	
	/**
	 * Setter
	 * @param status - the value to be set
	 */
	public void setStatus(ACCOUNT_STATUS status);
	
	/**
	 * Getter
	 * @return - the accountType field
	 */
	public ACCOUNT_TYPE getAccountType();
	
	/**
	 * Setter
	 * @param type - the value to be set
	 */
	public void setAccountType(ACCOUNT_TYPE type);
	
	/**
	 * Getter
	 * @return - the currencyId field
	 */
	public Integer getCurrencyId();
	
	/**
	 * Setter
	 * @param currencyId - the value to be set
	 */
	public void setCurrencyId(Integer currencyId); 
	
	/**
	 * Getter
	 * @return - the transactionList field
	 */
	public Customer getCustomer();
	
	/**
	 * Setter
	 * @param transactionList - the value to be set
	 */
	public void setCustomer(Customer customer);
	
//	public Long getCustomerId();
//	
//	public void setCustomerId(Long customerId);
//	
	/**
	 * Getter
	 * @return - the transactionList field
	 */
	public List<Transaction> getTransactionList();
	
	/**
	 * Setter
	 * @param transactionList - the value to be set
	 */
	public void setTransactionList(List<Transaction> transactionList);
		
}
