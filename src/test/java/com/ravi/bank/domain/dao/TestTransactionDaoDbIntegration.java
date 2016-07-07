/**
 * 
 */
package com.ravi.bank.domain.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ravi.bank.domain.service.dao.AccountDao;
import com.ravi.bank.domain.service.dao.CustomerDao;
import com.ravi.bank.domain.service.dao.TransactionDao;
import com.ravi.bank.domain.service.jpa.entities.Account;
import com.ravi.bank.domain.service.jpa.entities.Customer;
import com.ravi.bank.domain.service.jpa.entities.Transaction;

/**
 * Integration test that requires a real test database.
 * 
 * @author Ravi Aghera
 *
 */

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:spring-all-context.xml" })
public class TestTransactionDaoDbIntegration extends TestDaoDbIntegration {

	@Autowired
	ApplicationContext context;
	
	@Autowired
	private CustomerDao dao;
	
	@Autowired
	private AccountDao accDao;

	@Autowired
	private TransactionDao transDao;
	
	private Customer defaultCust;
	private Account defaultAcc;
	private Transaction defaultTrans;

	final private static Logger logger =  LoggerFactory.getLogger(TestTransactionDaoDbIntegration.class);
	
	@Before
	public void setUp() {

		//Default customer instance
		defaultCust = (Customer) context.getBean("testCustomer");
		defaultAcc = (Account) context.getBean("testAccount");
		defaultTrans = (Transaction) context.getBean("testTransaction");
		
		defaultCust.setEmail(generateNewEmail());
		
		assertNotNull(defaultCust);
		assertNotNull(defaultAcc);
		assertNotNull(defaultTrans);
		
	}
	
	/**
	 * Attempts but should not work.
	 * 
	 */
	@Test
	@Transactional
	@Rollback(value=false)
	public void testInsertReadUpdateDeleteTransWithNoAccount() {

		//Insert
		defaultAcc.setCustomer(defaultCust);
		defaultTrans.setAccount(defaultAcc);
		transDao.addTransaction(defaultTrans);
		
		//Check Trans
		assertNotNull(defaultTrans.getTransactionId());
		assertNotNull(defaultTrans.getAccount());
		assertNotNull(defaultTrans.getAccount().getAccountId());

		//Check Account
		assertNotNull(defaultAcc);
		assertNotNull(defaultAcc.getAccountId());
		assertNotNull(defaultAcc.getTransactionList());
		assertNotNull(defaultAcc.getTransactionList().get(0));
		assertNotNull(defaultAcc.getTransactionList().get(0).getTransactionId());

		//Check Customer
		assertNotNull(defaultCust);
		assertNotNull(defaultCust.getAccountsList());
		assertNotNull(defaultCust.getAccountsList().get(0));
		assertNotNull(defaultCust.getAccountsList().get(0).getTransactionList());
		
		final Long accId = defaultAcc.getAccountId();
		final Long transId = defaultTrans.getTransactionId();
		
		//Check objects match
		assertEquals(transId, defaultAcc.getTransactionList().get(0).getTransactionId());
		assertEquals(defaultTrans, defaultAcc.getTransactionList().get(0));
		assertNotNull(accId);

		//Update
		Transaction transFromAcc = defaultAcc.getTransactionList().get(0);
		Double newAmount = transFromAcc.getAmount() + 20;
		transFromAcc.setAmount( newAmount );

		//Note no need to call DAO since we used persist and we are in the transactional context
		//JPA / Hibernate will take care of it.
		assertEquals( defaultTrans.getAmount(), transFromAcc.getAmount() );
		
		
	}

	@Test
	@Transactional
	@Rollback(false)
	public void testTimestampGenerated() {
	
		/*
		 * Timestamp is taken care of in JPA using the @PrePersist annotation on method in the Entity.
		 * So never have to set it.
		 */
		
		defaultAcc.setCustomer(defaultCust);
		//Date now = new Date();
		defaultTrans.setAccount(defaultAcc);
		//defaultTrans.setTimestamp( now );
		
		transDao.addTransaction(defaultTrans);

		//logger.debug("Current time: {}", now);
		assertNotNull(defaultTrans.getTimestamp());
		
		logger.debug("Timestamp received: {}", defaultTrans.getTimestamp());
		
		Transaction transFromDb = transDao.findTransactionById(defaultTrans.getTransactionId());
		
		logger.debug("Timestamp receivedFromDb: {}", transFromDb.getTimestamp() );
		
		assertNotNull( transFromDb.getTimestamp() );
		assertEquals(defaultTrans.getTimestamp(), transFromDb.getTimestamp());
		
	}
	
	

}
