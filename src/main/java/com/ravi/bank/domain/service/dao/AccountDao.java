/**
 * 
 */
package com.ravi.bank.domain.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ravi.bank.domain.service.jpa.entities.Account;
import com.ravi.bank.domain.service.jpa.entities.AccountImpl;

/**
 * Data Access Object for Accounts
 * 
 * @author Ravi Aghera
 *
 */
public interface AccountDao {

	/**
	 * Finds an account using the id passed
	 * @param accountId
	 * @return - Account object from database
	 */
	public Account findAccountById(Long accountId);
	
	/**
	 * Updates an existing account record into the database
	 * @param account
	 * @return - Updated Account
	 */
	public Account updateAccount(Account account);
	
	/**
	 * Adds a new account record into the database
	 * @param account
	 */	
	public void addAccount(Account account);
	
	
	/**
	 * Removes an account record from the database
	 * identified by the id parameter.
	 * @param id
	 */
	public void deleteAccountById(Long id);
	
	public void deleteAccount(Account account);
	
	public List<Account> getAllAccounts();
	
	public Account findAccountByIdWithTransactions(final Long accountId);
	
	
	
}
