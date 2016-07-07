/**
 * 
 */
package com.ravi.bank.domain.service.jpa.entities;

/**
 * A Data Domain Object for the Transaction_Type database table
 * 
 * @author Ravi Aghera
 *
 */
public interface TransactionType {
	
	/**
	 * Getter
	 * @return - the transactionTypeId field
	 */
	Long getTransactionTypeId();
	
	/**
	 * Setter
	 * @param transactionTypeId - the value to be set
	 */
	void setTransactionTypeId(Long id);
	
	/**
	 * Getter
	 * @return - the transactionTypeName field
	 */
	String getTransactionTypeName();
	
	/**
	 * Setter
	 * @param transactionTypeName - the value to be set
	 */

	void setTransactionTypeName(String transTypeName);

}
