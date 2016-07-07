/**
 * 
 */
package com.ravi.bank.domain.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.Metamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ravi.bank.domain.service.dao.AccountDao;
import com.ravi.bank.domain.service.dao.TransactionDao;
import com.ravi.bank.domain.service.jpa.entities.Account;
import com.ravi.bank.domain.service.jpa.entities.AccountImpl;
import com.ravi.bank.domain.service.jpa.entities.Customer;
import com.ravi.bank.domain.service.jpa.entities.CustomerImpl;
import com.ravi.bank.domain.service.jpa.entities.Transaction;
import com.ravi.bank.domain.service.jpa.entities.TransactionImpl;

/**
 * Responsible for main DB operations relating to the Transaction Entity
 * and related Transaction database table.
 * 
 * @author Ravi Aghera
 *
 */
@Repository(value="transactionDao")
@Transactional
public class TransactionDaoImpl implements TransactionDao {

	@PersistenceContext
	@Autowired
	EntityManager em;
	
	@Autowired
	AccountDao accDao;
	
	@Override
	public Transaction findTransactionById(Long transId) {
		return em.find(TransactionImpl.class, transId);
	}

	@Override
	public void addTransaction(Transaction trans) {
		
		if(null == trans.getAccount()) {
			throw new IllegalArgumentException("A Transaction MUST contain an Account object if you want to insert into the DB");
		}

		//Ensure the new trans is related to the Account entity
		Account acc = trans.getAccount();
		if( null == acc.getTransactionList() ) {
			acc.setTransactionList(new ArrayList<Transaction>());
		}
		acc.getTransactionList().add(trans);
		
		//Persist Account first since we need an AccountId to persist the Trans
		//em.persist(acc);
		accDao.addAccount(acc);
		
		em.persist(trans);
		
	}

	@Override
	public void updateTransaction(Transaction trans) {
		em.merge(trans);
	}
	
	@Override
	public void deleteTransaction(Transaction trans) {
		em.remove(trans);
		
	}
	
	@Override
	public void deleteTransactionById(Long transId) {
		final Transaction trans = findTransactionById(transId);
		em.remove(trans);
		
	}

	@Override
	public List<Transaction> getAllTransactions() {
		
		List<Transaction> result = null;
		TypedQuery<Transaction> getAllQuery = em.createQuery("SELECT t FROM TransactionImpl t", Transaction.class);
		result = getAllQuery.getResultList();

		return result;
		
	}
	
	@Override
	public List<Transaction> getTransactions(final Long accountId) {

		
		
		List<Transaction> transList = null;
		
		
		//Get metamode and generated metamode entity type to use in joins.
		Metamodel m = em.getMetamodel();
		EntityType<AccountImpl> AccountImpl_ = m.entity(AccountImpl.class); 
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<AccountImpl> cq = cb.createQuery(AccountImpl.class);
		Root<AccountImpl> accountRoot = cq.from(AccountImpl.class);
		CriteriaQuery<AccountImpl> select = cq.where( cb.equal(accountRoot.get("accountId"), accountId) );
		
		TypedQuery<AccountImpl> query = em.createQuery(select);
		Account resultAcc = query.getSingleResult();
		
		return resultAcc.getTransactionList();
		
		//Transaction
//		EntityType<Transaction> Transaction_ = m.entity(Transaction.class);
//		CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
//		Root<Transaction> transRoot = cq.from(Transaction.class);
		
		//Account
//		EntityType<Account> Account_ = m.entity(Account.class); 
//		Attribute attr = Account_.getAttribute("transactionList");
//		CriteriaQuery<Account> cqAcc = cb.createQuery(Account.class);
//		Root<Account> accRoot = cqAcc.from(Account.class);
//		//Join<Account, Transaction> join = accRoot.join("transactionList");
//		Path<Object> path = accRoot.join("transactionList").get("accountId");
//		
//		CriteriaQuery<Account> query = cqAcc.select(accRoot);

		//		cqAcc.where( accRoot.get("transactionList").isNotNull() );
//		cqAcc.where( accRoot.get("transactionList").in("", "") );
		
		
	}
	

}
