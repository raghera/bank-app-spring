/**
 * 
 */
package com.ravi.bank.domain.service.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ravi.bank.domain.service.dao.CustomerDao;
import com.ravi.bank.domain.service.jpa.entities.Account;
import com.ravi.bank.domain.service.jpa.entities.Customer;
import com.ravi.bank.domain.service.jpa.entities.CustomerImpl;

/**
 * @author Ravi Aghera
 *
 */
@Repository(value="customerDao")
@Transactional(propagation=Propagation.REQUIRED, timeout=30)
public class CustomerDaoImpl implements CustomerDao{

	final private static Logger logger =  LoggerFactory.getLogger(CustomerDaoImpl.class);
	
	@PersistenceContext
	@Autowired
	private EntityManager em;
	
	@Override
	public Long addCustomer(Customer customer) {
		
		em.persist(customer);

		List<Account> accountList = customer.getAccountsList();
		if(null != accountList ) {
			for (Account account : accountList) {
				account.setCustomer(customer);
				em.persist(account);
			}
		}
		
		return customer.getCustomerId();
	}

	@Transactional(readOnly=true)
	@Override
	public Customer findCustomerById(Long customerId) {
		final Customer result = em.find(CustomerImpl.class, customerId);
		return result;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return em.merge(customer);		
	}

	@Override
	public void deleteCustomer(Customer customer) {
		em.remove(customer);
		
	}

	@Override
	public void deleteCustomerById(Long customerId) {
		Customer cust = this.findCustomerById(customerId);
		em.remove(cust);
	}
	
	@Override
	public List<Customer> getAllCustomers() {

		TypedQuery<Customer> getAllQuery = em.createQuery("SELECT c FROM CustomerImpl c ORDER BY c.customerId desc ", Customer.class);
		 List<Customer> customers = getAllQuery.getResultList();
//		 for (Customer customer : customers) {
//			List<Account> accList = customer.getAccountsList();
//			if(accList != null && accList.size() > 0) {
//				logger.info("AccountId in DAO LAZY: {}",accList.get(0).getAccountId());
//			}
//		}
		 return customers;
		
	}
	
}
