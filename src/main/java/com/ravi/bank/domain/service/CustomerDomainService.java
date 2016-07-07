/**
 * 
 */
package com.ravi.bank.domain.service;

import java.util.List;

import com.ravi.bank.domain.service.jpa.entities.Customer;
import com.ravi.bank.domain.service.jpa.entities.Transaction;

/**
 * Service provides all operations within the Customer domain.
 * 
 * @author Ravi Aghera
 *
 */
public interface CustomerDomainService {

	public Customer getNewestCustomer();
	
	/**
	 * Get details of the Customer related to the e-mail address param.
	 * The e-mail address is a unique identifier for a customer.
	 * @param email
	 * @return The Customer record matched.
	 */
	public Customer getCustomerDetails(String email);
	
	/**
	 * Add a new Customer record to persistent storage.
	 * @param customer
	 * @return The Id of the newly created Customer
	 */
	public Long addNewCustomer(Customer customer);
	
	/**
	 * Get all transactions related to a particular accountId
	 * @param accountId
	 * @return The list of matched transactions
	 */
	public List<Transaction> getTransactions(Long accountId);
	
}
