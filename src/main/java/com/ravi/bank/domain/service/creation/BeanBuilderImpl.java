/**
 * 
 */
package com.ravi.bank.domain.service.creation;

import com.ravi.bank.domain.service.ddo.AccountSummary;
import com.ravi.bank.domain.service.ddo.AccountSummaryImpl;
import com.ravi.bank.domain.service.ddo.CustomerSummary;
import com.ravi.bank.domain.service.ddo.CustomerSummaryImpl;
import com.ravi.bank.domain.service.ddo.TransactionSummary;
import com.ravi.bank.domain.service.ddo.TransactionSummaryImpl;
import com.ravi.bank.domain.service.jpa.entities.Account;
import com.ravi.bank.domain.service.jpa.entities.Customer;
import com.ravi.bank.domain.service.jpa.entities.Transaction;

/**
 * Gets Entity objects and creates Summary bean objects out of them.
 * The Summary objects are decoupled from the DB and JPA and are intended to be 
 * passed outside the Bank domain.  For instance it is passed to the Web Tier.
 * 
 * @author Ravi Aghera
 *
 */
public class BeanBuilderImpl implements BeanBuilder {

	@Override
	public AccountSummary createAccountSummary(Account account) {
		
		ArgValidator.validateAccountArg(account);
		
		final AccountSummary summary = new AccountSummaryImpl.AccountSummaryBuilder(account.getAccountId(), account.getBalance(), 
				account.getCustomer().getCustomerId(), account.getCurrencyId()).accountStatus(account.getStatus().getName()).build();
		
		return summary;
	}

	@Override
	public CustomerSummary createCustomerSummary(Customer customer) {

		ArgValidator.validateCustomerArg(customer);
		
		final CustomerSummary summary = new CustomerSummaryImpl(customer);
		
		return summary;
	}

	@Override
	public TransactionSummary createTransactionSummary(Transaction transaction) {
		
		final TransactionSummary summary = new TransactionSummaryImpl(transaction);
		
		return summary;
	}

	
	/*
	 * Enclosed class to validate we have the correct arguments coming into this service.
	 */
	private static class ArgValidator {
		
		private static boolean validateAccountArg(Account account) {
		
			if(null == account || null == account.getAccountId() ) {
				throw new IllegalArgumentException("Attempted to create a AccountSummary with a null Account Entity or an Account object with no id. " ); 
			}
			
			if(null == account.getCustomer()) {
				throw new IllegalArgumentException("Account object MUST be related to a Customer. This accountId is not: " + account.getAccountId()); 
			}
			
			return true;
		}
		
		private static boolean validateCustomerArg(Customer cust) {
			
			if(null == cust || null == cust.getCustomerId()) {
				throw new IllegalArgumentException("Attempted to create a CustomerSummary with a null Customer Entity or a Customer object with no id. " ); 
			}
			
			return true;
		}
		
		private static boolean validateTransactionArg(Transaction trans) {
			
			if(null == trans || null == trans.getTransactionId() ) {
				throw new IllegalArgumentException("Attempted to create a TransactionSummary with a null Transaction Entity or a Transaction object with no id. " ); 
			}
			
			return true;
			
		}
		
	}


	
}
