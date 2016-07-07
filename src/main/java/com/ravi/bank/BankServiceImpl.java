/**
 * 
 */
package com.ravi.bank;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ravi.bank.domain.service.AccountDomainService;
import com.ravi.bank.domain.service.CustomerDomainService;
import com.ravi.bank.domain.service.jpa.entities.Account;
import com.ravi.bank.domain.service.jpa.entities.Customer;
import com.ravi.bank.domain.service.jpa.entities.Transaction;

/**
 * Implementation of the BankService
 * 
 * @author Ravi Aghera
 *
 */
@Component("bankService")
public class BankServiceImpl implements BankService {

	@Autowired
	private CustomerDomainService customerService;
	
	@Autowired
	private AccountDomainService accountService;
	
	final private static Logger logger =  LoggerFactory.getLogger(BankServiceImpl.class);
	
	@Override
	public Customer getNewestCustomerDetails() {
		return customerService.getNewestCustomer();
	}

	@Override
	public Customer getCustomerDetails(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerDetails(String email) {
		
		Customer customer = null;
		try {
			customer = customerService.getCustomerDetails(email);
		}
		catch(Throwable thr) {
			
			logger.error("Error during Customer#getCustomerDetails. Returning a null customer. ", thr );
			
		}
		
		return customer;
		
	}

	@Override
	public Long createNewCustomer(Customer customerDetails) {

		return customerService.addNewCustomer(customerDetails);
		
	}
	
	@Override
	public List<Transaction> getTransactionsByAccountId(Long accountId) {
		
		throw new UnsupportedOperationException("Method implementation is not available yet in BankServiceImpl.");
		
	}
	
	@Override
	public Account getAccountWithTransactions(Long accountId) {
		Account account = accountService.getAccountWithTransactions(accountId);
		return account;
	}

}
