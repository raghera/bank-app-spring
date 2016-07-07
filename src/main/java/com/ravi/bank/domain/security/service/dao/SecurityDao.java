/**
 * 
 */
package com.ravi.bank.domain.security.service.dao;

import org.aspectj.apache.bcel.classfile.Code;

import com.ravi.bank.domain.security.service.jpa.entities.Authority;
import com.ravi.bank.domain.security.service.jpa.entities.User;

/**
 * @author Ravi Aghera
 *
 */
public interface SecurityDao {

	/**
	 * Adds a User to persistent storage.
	 * The associated AuthorityList within the User is not stored.
	 * Needs to be persisted seperately or use {@link Code}addNewUserAndAssociatedAuthorityList
	 * 
	 * @param user
	 * @return
	 */
	void addNewUser(User user);

	/**
	 * Rather than have the service layer do all this logic this operation
	 * allows you to do it in one place.
	 * Pass a User containing an AuthorityList you also want to persist
	 * Method will add User and #getAthorityList() and loop through them all and persist them.
	 * As it is doing so it will also check that each Authority is associated to the passed
	 * User.getUserId field.
	 * 
	 * @param user - The user to be persisted and its related Authority objects to be persisted.
	 */
	void addNewUserAndAssociatedAuthorityList(User user);
	
	User findUserById(Long id);
	
	/**
	 * This deletes a User Entity and it's related Authority Entities.
	 * An Authority cannot exist without a User, 
	 * so deleting it removes them too.  
	 * 
	 * @param user
	 * @return The username deleted
	 */
	void deleteUser(User user);
	
	void deleteUserById(Long id);
	
	User updateUser(User user);

	
	void addNewAuthority(Authority authority);
	
	Authority findAuthorityById(Long id);
	
	/**
	 * 
	 * @param user
	 * @return The username deleted
	 */
	void deleteAuthority(Authority authority);
	
	void deleteAuthorityById(Long id);
	
	Authority updateAuthority(Authority authority);

	
	
	
}
