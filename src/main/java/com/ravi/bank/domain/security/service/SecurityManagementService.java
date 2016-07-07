/**
 * 
 */
package com.ravi.bank.domain.security.service;

import com.ravi.bank.domain.security.service.jpa.entities.User;
import com.ravi.bank.domain.service.jpa.entities.Customer;

/**
 * Responsible for the Security domain and interacting with 
 * the Security DB.
 * 
 * @author Ravi Aghera
 *
 */
public interface SecurityManagementService {

	public User addUser(Customer customer);
	
	public User addUserAndAuthority(Customer customer);
	
	/**
	 * Removes the User.
	 * Note any Authority objects linked to this User
	 * Should be removed first
	 * @param user
	 */
	public void removeUser(User user);
	
	/**
	 * Removes all the Authorities associated with this User
	 * and the User itself.
	 * @param user
	 */
	public void removeUserAndAuthorities(User user);
	
	
	public void modifyUser(User user);
	
}
