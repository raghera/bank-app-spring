/**
 * 
 */
package com.ravi.bank.domain.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
import com.ravi.bank.domain.service.jpa.entities.AccountImpl;
import com.ravi.bank.domain.service.jpa.entities.Customer;
import com.ravi.bank.domain.service.jpa.entities.Transaction;
import com.ravi.bank.domain.service.jpa.entities.Account.ACCOUNT_STATUS;
import com.ravi.bank.domain.service.jpa.entities.Account.ACCOUNT_TYPE;

/**
 * 
 * Integration test for the AccountDao
 * Requires a real database connection
 * 
 * @author Ravi Aghera
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:spring-all-context.xml" })
public class TestAccountDaoDbIntegration extends TestDaoDbIntegration {
	
	final private static Logger logger =  LoggerFactory.getLogger(TestAccountDaoDbIntegration.class);
		
	@Autowired
	ApplicationContext context;
	
	@Autowired
	private CustomerDao custDao;
	
	@Autowired
	private AccountDao accDao;
	
	@Autowired
	private TransactionDao transDao;
	
	private Customer defaultCust;
	private Account defaultAcc;
	private Transaction defaultTrans;

	
	@BeforeClass
	public static void classSetUp() {
		logger.info("No class set up");
	}
	
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
	
	@After
	public void tearDown() {
		logger.info("running teardown ...");
	}
	
	/**
	 * Account MUST have a Customer related to it.
	 * Transaction MUST have an Account related to it.
	 * Account can exist without a Transaction.
	 * Customer can exist without an Account.
	 * 
	 */
	
	@Test
	@Transactional
	@Rollback(value=false)
	public void testInsertAccountCustomerRelationship() {
		
		logger.info("Start test: testAddAccount");
		
		List<Transaction> transList = new ArrayList<Transaction>();
		transList.add(defaultTrans);
		defaultAcc.setTransactionList(transList);
		
		defaultAcc.setCustomer(defaultCust);
		
		//Insert
		accDao.addAccount(defaultAcc);
		assertNotNull(defaultAcc.getAccountId());
		assertNotNull(defaultCust.getCustomerId() );
		assertNotNull(defaultTrans.getTransactionId() );
		
		final Long origAccId = defaultAcc.getAccountId();
		final Long origCustId = defaultCust.getCustomerId();
		final Long origTransId = defaultTrans.getTransactionId();
		
		logger.info("testInsertAccountCustomerRelationship.  AccId: {} CustId: {} TransId: {}", new Object[] {origAccId, origCustId, origTransId});
		
		//Read
		Account persistedAcc = accDao.findAccountById(defaultAcc.getAccountId());
		Customer custFromAcc = persistedAcc.getCustomer();
		assertNotNull(persistedAcc);
		assertNotNull(custFromAcc);
		assertNotNull(persistedAcc.getAccountId());
		assertNotNull(custFromAcc.getCustomerId());
		assertEquals(defaultAcc.getAccountId(), persistedAcc.getAccountId());

		assertNotNull(persistedAcc.getTransactionList());
		assertNotNull(persistedAcc.getTransactionList().get(0));
		assertNotNull(persistedAcc.getTransactionList().get(0).getTransactionId());
		Transaction transFromAcc = persistedAcc.getTransactionList().get(0);
		assertEquals(origTransId, transFromAcc.getTransactionId());
		
		//Update
		persistedAcc.setBalance(defaultAcc.getBalance() + 10);
		custFromAcc.setFirstName("CHANGED");
		transFromAcc.setAmount(22.0);
		
		//Check updates
		Account updatedAcc = accDao.findAccountById( origAccId );
		assertNotNull(persistedAcc);
		assertEquals( new Double(110.0) , updatedAcc.getBalance());
		
		Customer updatedCust = custDao.findCustomerById(origCustId);
		assertNotNull(updatedCust);
		assertEquals("CHANGED", updatedCust.getFirstName());
		
		Transaction updatedTrans = transDao.findTransactionById(origTransId);
		assertNotNull(updatedTrans);
		assertEquals(new Double(22.0), updatedTrans.getAmount());
	
		
		//deleteAccount(account.getAccountId());
		
	}
	
	//testGetAllAndUpdate
	
	/**
	 * Assumes an existing Account in the DB.
	 */
	@Test
	@Transactional
	@Rollback(value=false)
	public void testUpdateOrJpaMerge() {
		
		List<Account> accList = accDao.getAllAccounts();
		assertNotNull(accList);
		assertNotNull(accList.get(0));
		Account account = accList.get(0);
		assertNotNull(account.getCustomer());
		
		Account newAcc = new AccountImpl();
		Double newBalance = account.getBalance() + 10.0; 
		newAcc.setBalance( newBalance );
		newAcc.setAccountType(ACCOUNT_TYPE.SAVINGS);
		newAcc.setCurrencyId(2);
		newAcc.setCustomer(account.getCustomer());
		newAcc.setStatus(ACCOUNT_STATUS.CLOSED);
		//newAcc.setTransactionList(account.getTransactionList());
		
		Account updatedAcc = accDao.updateAccount(newAcc);
		assertNotNull(updatedAcc);
		assertNotNull(updatedAcc.getBalance());
		assertEquals( newBalance, updatedAcc.getBalance());
		
	}
	
	
	/**
	 * Testing Delete.
	 * DB Constraints are:
	 * 
	 * You can only delete an Account if it does not belong to a Customer (shouldn't be possible)
	 * You can only delete a Transaction if it does not belong to an Account
	 * You can only delete an Account if it has no related entries in the Transaction table 
	 * If you delete a Customer it deletes it's related Account but not if it has any transactions.
	 * When you delete a Customer it does not cascade the delete to it's related Transactions.
	 * 
	 */
	
	/**
	 * Find an Account and its related Customer and delete the Account.
	 * Customer should still be there.
	 */
	@Test
	@Transactional
	@Rollback(value=false)
	public void deleteTransactionAndAccount() {
		
		Account account = null;
		
		List<Account> accList = accDao.getAllAccounts();
		assertNotNull(accList);

		//Find an Account that has some transactions.
		for (Account acc : accList) {
			if( null != acc.getTransactionList() ) {
				account = acc;
				break;
			}
		}
		assertNotNull("Could not find an account with Transactions in test setup ", account);
		assertNotNull(account.getAccountId());
		assertNotNull("Customer not found in Account: " + account.getAccountId(), account.getCustomer());
		assertNotNull(account.getCustomer().getCustomerId());
		
		Long accId = account.getAccountId();
		Long custId = account.getCustomer().getCustomerId();
		List<Transaction> transList = account.getTransactionList();

		//Delete all Transactions
		for (Transaction trans : transList) {
			Long transId = trans.getTransactionId();
			transDao.deleteTransaction(trans);
			Transaction deletedTrans = transDao.findTransactionById(transId);
			assertNull(deletedTrans);
		}
		
		//Delete Account
		accDao.deleteAccount(account);
		Account deletedAcc = accDao.findAccountById( accId );
		assertNull(deletedAcc);

		//Customer should still be there
		Customer deletedCust = custDao.findCustomerById( custId );
		assertNotNull(deletedCust);
		assertEquals(custId, deletedCust.getCustomerId());

		
	}
	
	@Test
	public void testAllTransactionsForAccountId() {
		
		List<Account> accountList = accDao.getAllAccounts();
		assertNotNull(accountList);
		assertNotNull(accountList.get(0));
		
		Long accountId = accDao.getAllAccounts().get(0).getAccountId();
		List<Transaction> transList = transDao.getTransactions(accountId);
		
		assertNotNull(transList);
		
	}
	
}
