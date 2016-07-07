/**
 * 
 */
package com.ravi.bank.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ravi.bank.domain.security.service.SecurityManagementService;
import com.ravi.bank.domain.service.dao.CustomerDao;
import com.ravi.bank.domain.service.jpa.entities.Account;
import com.ravi.bank.domain.service.jpa.entities.Customer;
import com.ravi.bank.domain.service.jpa.entities.Transaction;

/**
 * Customer Domain Service is where the Customer related Business Logic
 * should be implemented.
 * 
 * May delegate some of the work to other Domain Services or POJO classes 
 * but all Customer business logic must be instigated from here.
 * 
 * @author Ravi Aghera
 *
 */
@Component("customerService")
@Transactional
public class CustomerDomainServiceImpl implements CustomerDomainService {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	SecurityManagementService securityService;
	
	@Override
	public Customer getNewestCustomer() {

		//Should be ordered in terms of Id which are sequenced. - Could change to time based.
		List<Customer> custList = customerDao.getAllCustomers();
		
		if(null == custList || custList.isEmpty()) {
			return null;
		}
		
		Customer cust = custList.get(0);
		Long Id = cust.getCustomerId();
		String email = cust.getEmail();
		
		return custList.get(0);
	}

	@Override
	public Customer getCustomerDetails(String email) {
		
		Customer returnValue = null;
		
		/*
		 * TODO - DAO should allow you to search via e-mail address.
		 * This is a unique key.  Then can delete this for loop.
		 */
		
		List<Customer> customerList = customerDao.getAllCustomers();
		
		for (Customer customer : customerList) {
			if(customer.getEmail() != null && customer.getEmail().equals(email)) {
				returnValue = customer;
				break;
			}
		}
		
		//TODO - Throw Business Exception here.
		if(null == returnValue) {
			throw new RuntimeException("A Customer record with the email: " + email + " does not exist.  Please register as a new customer.");
		}
		
		List<Account> accList =  returnValue.getAccountsList();
		for (Account account : accList) {
			account.getAccountId();
		}
		
		return returnValue;
	}
	
	@Override
	public Long addNewCustomer(Customer customer) {

		//Add to the security domain then into Bank domain
		securityService.addUserAndAuthority(customer);
		return customerDao.addCustomer(customer);

		
		
	}
	
	@Override
	public List<Transaction> getTransactions(Long accountId) {
		
		throw new UnsupportedOperationException("Method has not been implemented yet.");
		
	}

}
