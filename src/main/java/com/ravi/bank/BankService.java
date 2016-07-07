/**
 * 
 */
package com.ravi.bank;

import java.util.List;

import com.ravi.bank.domain.service.AccountDomainService;
import com.ravi.bank.domain.service.CustomerDomainService;
import com.ravi.bank.domain.service.TransactionDomainService;
import com.ravi.bank.domain.service.jpa.entities.Account;
import com.ravi.bank.domain.service.jpa.entities.Customer;
import com.ravi.bank.domain.service.jpa.entities.Transaction;

/**
 * The Service that every external Java Client will call
 * Abstracts the whole application into one Interface.  Web front end will
 * call this directly and any WSDL based Api will call this.
 * 
 * It will interact with the {@link AccountDomainService}
 * {@link TransactionDomainService} and {@link CustomerDomainService}.
 * 
 * Interacts with the {@link ClientResponseService} to provide client facing 
 * responses.
 * 
 * @author Ravi Aghera
 *
 */
public interface BankService {

	//TODO These methods are currently for testing only.  
	//Generally don't want to transfer an Entity to the web framework so
	//these will return CustomerSummary, AccountSummary, TransactionSummary 
	//objects only

	//Probably want 1 getCustomerDetails method taking a filter object telling you what to get.
	
	public Customer getNewestCustomerDetails();
	
	public Customer getCustomerDetails(Long customerId);
	
	//Email maps to username in the frontend.
	public Customer getCustomerDetails(String email);
	
	public Long createNewCustomer(Customer customerDetails);
	
	public List<Transaction> getTransactionsByAccountId(Long accountId);
	
	public Account getAccountWithTransactions(Long id);
	
}
