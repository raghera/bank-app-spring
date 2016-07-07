/**
 * 
 */
package com.ravi.bank.domain.service.dao;

import java.util.List;

import com.ravi.bank.domain.service.jpa.entities.Customer;

/**
 * @author Ravi Aghera
 *
 */
public interface CustomerDao {

	/**
	 * Finds a Customer using the id passed
	 * @param customerId
	 * @return - Customer object from database
	 */
	public Customer findCustomerById(Long customerId);
	
	public Long addCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer);
	
	public void deleteCustomer(Customer customer);
	
	public void deleteCustomerById(Long customerId);
	
	public List<Customer> getAllCustomers();
	
}
