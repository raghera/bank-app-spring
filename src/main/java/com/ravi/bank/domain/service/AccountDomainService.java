/**
 * 
 */
package com.ravi.bank.domain.service;


import com.ravi.bank.domain.service.jpa.entities.Account;

/**
 * Service provides all operations within the Account domain.
 * 
 * @author Ravi Aghera
 *
 */
public interface AccountDomainService {

	public Account getAccountWithTransactions(Long accountId);
	
}
