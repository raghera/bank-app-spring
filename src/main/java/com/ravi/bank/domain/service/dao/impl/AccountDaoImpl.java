/**
 * 
 */
package com.ravi.bank.domain.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.mapping.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ravi.bank.domain.service.dao.AccountDao;
import com.ravi.bank.domain.service.dao.CustomerDao;
import com.ravi.bank.domain.service.jpa.entities.Account;
import com.ravi.bank.domain.service.jpa.entities.AccountImpl;
import com.ravi.bank.domain.service.jpa.entities.Customer;
import com.ravi.bank.domain.service.jpa.entities.Transaction;

/**
 * 
 * Data access object implementation.
 * Uses Spring and JPA with the Hibernate implementation.
 * 
 * Also uses Spring declarative Transactions.
 * 
 * @author Ravi Aghera
 *
 */
@Repository(value="accountDao")
@Transactional //Note this is the Spring annotation using Spring Transactions
public class AccountDaoImpl implements AccountDao{

	//This will be injected by the Spring container
	@PersistenceContext
	@Autowired
	private EntityManager em;
	
	@Autowired
	private CustomerDao custDao;
	
	final private static Logger logger =  LoggerFactory.getLogger(AccountDaoImpl.class);
	
	@Override
	public Account findAccountById(final Long accountId) {
		
//		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//		CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);

		final Account result = em.find(AccountImpl.class, accountId);
		
		return result;
	}
	
	@Override
	public Account findAccountByIdWithTransactions(Long accountId) {
		final Account result = em.find(AccountImpl.class, accountId);
		
		//TODO change this - move to transfer objects.
		//This is to force loading of the transactionList (try and do a joinfetch if this is required.) 
//		if( null == result.getTransactionList()) {
		List<Transaction> list = result.getTransactionList();
		logger.info("Size of TransactionList: {} ", list.size() );
		
		Long transId = list.get(0).getTransactionId();
		logger.info("Size of TransId: {}", transId );
		
//		}
		
		return result;
	}

	@Override
	public Account updateAccount(final Account account) {
		return em.merge(account);
	}
	
	@Override
	public void addAccount(final Account account) {
		
		//Must have a Customer related to an Account
		Customer cust = account.getCustomer();
		if(cust == null) {
			throw new IllegalArgumentException("Account argument does not have a Customer related to it."
					+ " An Account must be related to a Customer.");
		}
		em.persist(account);
		
		//TODO move this into its own method.
		//persistAccountInCustomer(cust.getAccountsList());
		
		//Ensure Customer is related to the Account and persist
		List<Account> accList = cust.getAccountsList();
		if(null == accList) {
			accList = new ArrayList<Account>();
		}

		accList.add(account);
		cust.setAccountsList(accList);
		
		custDao.addCustomer(cust);
		//em.persist(cust);
		
		persistTransactions(account.getTransactionList(), account);
		
	}

	@Override
	public void deleteAccountById(final Long id) {
		
		Account account = this.findAccountById(id);
		em.remove(account);
		
	}

	@Override
	public void deleteAccount(Account account) {
		em.remove(account);
	}
	
	/*
	 * TODO 
	 * Need to introduce some filters to make this more flexible. 
	 * Also put some of this boilerplate code into an abstract class
	 * 
	 * (non-Javadoc)
	 * @see com.ravi.bank.domain.service.dao.AccountDao#getAllAccounts()
	 */
	@Override
	public List<Account> getAllAccounts() {

		List<Account> result = null;
		TypedQuery<Account> getAllQuery = em.createQuery("SELECT a FROM AccountImpl a ORDER BY a.accountId desc", Account.class);
		result = getAllQuery.getResultList();

		return result;
		
	}
	
	private void persistTransactions(List<Transaction> transList, Account account) {
		if(null != transList) {
			for (Transaction transaction : transList) {
				transaction.setAccount(account);
				em.persist(transaction);
			}
		}
	}
	
	
}
