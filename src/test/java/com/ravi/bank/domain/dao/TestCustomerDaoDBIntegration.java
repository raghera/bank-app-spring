/**
 * 
 */
package com.ravi.bank.domain.dao;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
import com.ravi.bank.domain.service.jpa.entities.Account;
import com.ravi.bank.domain.service.jpa.entities.Customer;
import com.ravi.bank.domain.service.jpa.entities.CustomerImpl;

/**
 * An Integration test for the Customer table in the DB.
 * Requires a real database as we will test the state of columns in it.
 * 
 * Customer has:
 * 	1 or many Accounts
 * 	1 CustomerType
 * 
 * @author Ravi Aghera
 *
 */
@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:spring-all-context.xml" })
public class TestCustomerDaoDBIntegration extends TestDaoDbIntegration {
	
	@Autowired
	ApplicationContext context;

	//@InjectMocks
//	@Mock
//	private EntityManager em;
	
	@Autowired
	private CustomerDao dao;
	
	@Autowired
	private AccountDao accDao;
	
	Customer defaultCust;
	Account defaultAcc;
	
	//May want to autowire this?
	final private static Logger logger =  LoggerFactory.getLogger(TestCustomerDaoDBIntegration.class);

	@BeforeClass
	public static void classSetUp() {
		
	}
	
	@Before
	public void setUp() {

		//Default customer instance
		defaultCust = (Customer) context.getBean("testCustomer");
		defaultAcc = (Account) context.getBean("testAccount");
		
		defaultCust.setEmail(generateNewEmail());
		
		assertNotNull(defaultCust);
		assertNotNull(defaultAcc);
		
	}
	
	/**
	 * Basic test to insert a Customer and check.
	 * No Entity relationships so should be straight forward  
	 */
	
	@Test
	@Transactional
	@Rollback(value=false)
	public void testInsertReadUpdateDeleteCustWithNoAccount() {

		//persist customer
		dao.addCustomer(defaultCust);
		
		final Long origId = defaultCust.getCustomerId();
		assertNotNull(origId);
		
		Customer persistedCust = dao.findCustomerById(origId);
		
		//logger.debug("persistedCust: {}", persistedCust );
		
		assertNotNull(persistedCust);
		assertEquals("Difference between original object and persisted.", persistedCust, defaultCust);
		
		//Change, update, check the e-mail address
		final String newEmail = "raghera@gmail.com";
		persistedCust.setEmail(newEmail);
		dao.updateCustomer(persistedCust);
		
		Customer updatedCust = dao.findCustomerById( origId );
		assertNotNull(updatedCust);
		logger.debug("Updated email: {}", updatedCust.getEmail() );
		assertEquals(newEmail, updatedCust.getEmail());

		//Delete, check
		dao.deleteCustomer(updatedCust);
		Customer deletedCust = dao.findCustomerById(origId);
		assertNull("Customer id: {} should be deleted",deletedCust);
		
	}
	
	/**
	 * The DAO for Customer should ensure all the plumbing for setting up its Entity relationship
	 * with Customer is done.  Only 1 call to the CustomerDao should be required.
	 * 
	 * All the plumbing required is therefore done in one place.
	 * 
	 * Simply insert an Account into the Customer.accountList
	 * Persist the Customer and that's it.
	 */
	@Test
	@Transactional
	@Rollback(value=false) //Need a commit to check all constraints are met
	public void testEntityRelationshipIsEstablishedByDao() {

		/*
		 * Do some inserts and check the Entity relationships are set up correctly
		 */
		
		//Create,Add an AccountList to Customer
		List<Account> accList = new ArrayList<Account>();
		accList.add(defaultAcc);
		defaultCust.setAccountsList(accList);
		
		//Persist Customer only - Should take care of relationship with Account
		dao.addCustomer(defaultCust);
		assertNotNull(defaultCust.getCustomerId());
		
		//Check Account has correct Customer details
		//defaultAcc.setCustomer(defaultCust);
		assertNotNull(defaultAcc.getCustomer());
		assertNotNull(defaultAcc.getCustomer().getCustomerId());
		assertEquals("CustomerId's are different. ", defaultCust.getCustomerId(), defaultAcc.getCustomer().getCustomerId());
		//accDao.addAccount(defaultAcc);
		
		//Check the Account details are set for Customer
		assertNotNull(defaultCust.getAccountsList());
		assertNotNull(defaultCust.getAccountsList().get(0));
		assertNotNull(defaultCust.getAccountsList().get(0).getAccountId());
		assertEquals("CustomerId's are different. ", defaultAcc.getAccountId(), defaultCust.getAccountsList().get(0).getAccountId() );
		//Check they have the same
		
		logger.debug("testEntityRelationshipIsEstablishedByDao CustId: {} AccId: {}", defaultCust.getCustomerId(), defaultAcc.getAccountId());

		
		/*
		 * Everything ok in initial insert, now check retrievals are ok too
		 */
		
		//		
		//Find the Account and Customer check the CustomerId is correct in both
		Account accFromDb = accDao.findAccountById(defaultAcc.getAccountId());
		Customer custFromDb = dao.findCustomerById(defaultCust.getCustomerId());
		assertNotNull(accFromDb);
		assertNotNull(custFromDb);
		
		//Test account
		assertNotNull(accFromDb.getCustomer());
		assertEquals(defaultCust.getCustomerId(), accFromDb.getCustomer().getCustomerId());
		assertEquals(custFromDb.getCustomerId(), accFromDb.getCustomer().getCustomerId());
		
		//Check the AccountId details are correct
		assertNotNull(custFromDb.getAccountsList() );
		assertNotNull(custFromDb.getAccountsList().get(0) );
		assertNotNull(custFromDb.getAccountsList().get(0).getAccountId() );
		assertEquals(defaultAcc.getAccountId(), custFromDb.getAccountsList().get(0).getAccountId() );
		assertEquals(accFromDb.getAccountId(), custFromDb.getAccountsList().get(0).getAccountId() );
		
		
		logger.debug("CustIdFromAccount: {}, AccIdFromCust {}", accFromDb.getCustomer().getCustomerId(), custFromDb.getAccountsList().get(0).getAccountId() );
		
		//TODO - Do a delete and check they are gone.
		
	}
	
	

	/**
	 * Get existing Customer object
	 * update it and the related Account object and check.
	 * 
	 * Test assumes previous tests in this file have passed
	 */
	@Test
	@Transactional
	@Rollback(value=true) // only doing updates
	public void testGetAllAndUpdateCustWithAccount() {
		
		logger.debug("Start testUpdateCustWithAccount ...");
		
		List<Account> accList = new ArrayList<Account>();
		accList.add(defaultAcc);
		
		defaultCust.setAccountsList(accList);

		//Get all Customers
		List<Customer> allCustomerList = dao.getAllCustomers();
		assertNotNull(allCustomerList);
		assertNotNull(allCustomerList.get(0));
		
		//Get latest customer
		Customer custFromDb = allCustomerList.get(0);
		assertNotNull(custFromDb.getAccountsList());
		assertNotNull(custFromDb.getAccountsList().get(0));
		Account accFromCust = custFromDb.getAccountsList().get(0);
		
		logger.debug(" testUpdateCustWithAccount CustId: {}, AccId: {}", allCustomerList.get(0).getCustomerId(), accFromCust.getAccountId() );
				
		final Long origCustId = custFromDb.getCustomerId();
		final Long origAccId = accFromCust.getAccountId();

		//Data setup
		final String email = custFromDb.getEmail() + "Added";
		Double balance  = accFromCust.getBalance() + 5;
		
		custFromDb.setEmail(email);
		accFromCust.setBalance( balance );
		
		Customer updatedCust = dao.findCustomerById( origCustId );
		assertNotNull(updatedCust);
		assertNotNull(updatedCust.getAccountsList());
		assertNotNull(updatedCust.getAccountsList().get(0));

		assertNotNull(updatedCust.getEmail());
		assertEquals("Email not changed. ", email, updatedCust.getEmail());
		
		logger.debug("Updated email: {}", updatedCust.getEmail() );
		
		Account updatedAcc = accDao.findAccountById(origAccId);
		assertNotNull(updatedAcc);
		assertNotNull(updatedAcc.getCustomer());
		assertEquals(updatedAcc.getCustomer().getCustomerId(), origCustId);
		assertEquals(balance, updatedAcc.getBalance());
		
		logger.debug("Updated balance: {}", updatedAcc.getBalance() );
		
	}

	/**
	 * Tests adding Customer and Account separately
	 * and subsequently adding the relationship (outside the dao). 
	 */
	@Ignore(value="Remove? This scenario is not possible in design. We enforce DB rules in Dao")
	@Test
	@Transactional
	@Rollback(value=true) //checking this elsewhere
	public void testAccountCustomerEntityRelationshipOutsideDao() {
		
		List<Account> accList = new ArrayList<Account>();
		
		//Persist them both separately
		dao.addCustomer(defaultCust);
		
		accDao.addAccount(defaultAcc);
		
		//logger.debug("--Cust\n: {} \nAccId\n: {}", defaultCust, defaultAcc );
		
		logger.debug("CustId: {} AccId: {}", defaultCust.getCustomerId(), defaultAcc.getAccountId());
		
		//Add to each other
		accList.add(defaultAcc);
		defaultCust.setAccountsList(accList);
		defaultAcc.setCustomer(defaultCust);
		
		dao.updateCustomer(defaultCust);
		
		logger.debug("AccountFromCustomerId: {}", defaultCust.getAccountsList().get(0).getAccountId() );
		logger.debug("CustomerFromAccId: {}", defaultAcc.getCustomer().getCustomerId() );
		
		assertNotNull(defaultCust.getAccountsList());
		assertNotNull(defaultCust.getAccountsList().get(0));
		assertNotNull(defaultCust.getAccountsList().get(0).getAccountId());
		assertNotNull(defaultAcc.getCustomer() );
		
		defaultCust.setEmail( "raghera@gmail.com" );
		defaultAcc.setBalance(2000.0);
		
		Customer custFromAcc = defaultAcc.getCustomer();
		Account accFromCustomer = defaultCust.getAccountsList().get(0);
		
		assertNotNull(custFromAcc.getEmail());
		assertNotNull(accFromCustomer.getBalance());
		assertEquals("raghera@gmail.com",custFromAcc.getEmail());
		assertEquals(new Double(2000.0), accFromCustomer.getBalance());
		
		logger.debug("Email??: {}", custFromAcc.getEmail() );
		logger.debug("Balance??: {}", accFromCustomer.getBalance() );
		
		custFromAcc.setEmail("blahblahblah");
		accFromCustomer.setBalance(3333.0);
		logger.debug("Email??: {}", defaultCust.getEmail() );
		logger.debug("Balance??: {}", defaultAcc.getBalance() );
		
		assertNotNull(custFromAcc.getEmail());
		assertNotNull(accFromCustomer.getBalance());
		assertEquals("blahblahblah",custFromAcc.getEmail());
		assertEquals(new Double(3333.0), accFromCustomer.getBalance());
		
		
		//logger.debug("Cust\n: {} AccId\n: {}", defaultCust.getAccountsList().get(0), defaultAcc.getCustomer() );
		
	}
	
	@Test
	@Transactional
	@Rollback(value=false)
	public void testUpdateOrJpaMerge() {
		
		
		List<Customer> allCustomerList = dao.getAllCustomers();
		assertNotNull(allCustomerList);
		assertNotNull(allCustomerList.get(0));
		Customer custFromDb = allCustomerList.get(0);
		assertNotNull(custFromDb.getAccountsList());
		assertNotNull(custFromDb.getAccountsList().get(0));
		
		Customer cust = new CustomerImpl();
		String newEmail = generateNewEmail();
		cust.setCustomerId(custFromDb.getCustomerId());
		cust.setCustomerType(2L);
		cust.setFirstName("Raquel");
		cust.setSurname("Melchior");
		cust.setCountryCode("GB");
		cust.setEmail(newEmail);
		cust.setAccountsList(custFromDb.getAccountsList());
		
		//Persist Customer only - Should take care of relationship with Account
		//dao.addCustomer(defaultCust);
		Customer updatedCust = dao.updateCustomer(cust);
		assertNotNull( updatedCust );
		assertEquals(cust, updatedCust);

	}

	
}
