/**
 * 
 */
package com.ravi.bank.domain.service;

import com.ravi.bank.domain.service.ddo.AccountSummary;
import com.ravi.bank.domain.service.ddo.CustomerSummary;
import com.ravi.bank.domain.service.ddo.TransactionSummary;
import com.ravi.bank.domain.service.jpa.entities.Account;
import com.ravi.bank.domain.service.jpa.entities.Customer;

/**
 * Gets the data stored in Entity objects and puts them into
 * POJO's to act as domain data objects that can be passed to the
 * web tier.
 * 
 * All implementations ought to use the builder pattern to create objects
 * 
 * @author Ravi Aghera
 *
 */
public interface ConversionService {

	public AccountSummary convertAccountEntity(Account account);
	
	public CustomerSummary convertCustomerEntity(Customer customer);
	
	public TransactionSummary convertCustomerEntity(TransactionSummary transaction);
}
