/**
 * 
 */
package com.ravi.bank.domain.service.dao;

import java.util.List;

import com.ravi.bank.domain.service.jpa.entities.Transaction;

/**
 * @author Ravi Aghera
 *
 */
public interface TransactionDao {

	public Transaction findTransactionById(Long transId);
	
	public void addTransaction(Transaction trans);
	
	public void updateTransaction(Transaction trans);
	
	public void deleteTransaction(Transaction trans);
	
	public void deleteTransactionById(Long transId);
	
	public List<Transaction> getAllTransactions();
	
	public List<Transaction> getTransactions(Long accountId);
	
}
