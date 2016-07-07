/**
 * 
 */
package com.ravi.bank.domain.service.creation;

import com.ravi.bank.domain.service.ddo.AccountSummary;
import com.ravi.bank.domain.service.ddo.CustomerSummary;
import com.ravi.bank.domain.service.ddo.TransactionSummary;
import com.ravi.bank.domain.service.jpa.entities.Account;
import com.ravi.bank.domain.service.jpa.entities.Customer;
import com.ravi.bank.domain.service.jpa.entities.Transaction;

/**
 * Builds beans that can be transferred to clients of the domain 
 * services.  
 * Typically take a JPA Entity class and copy data into the a Pojo.
 *
 * Do not do any creation the other way around as Entity classes should
 * be retrieved from the DB first.
 * 
 * @author Ravi Aghera
 *
 */
public interface BeanBuilder {
	
	public AccountSummary createAccountSummary(Account account);
	
	public CustomerSummary createCustomerSummary(Customer customer);
	
	public TransactionSummary createTransactionSummary(Transaction transaction);

}
