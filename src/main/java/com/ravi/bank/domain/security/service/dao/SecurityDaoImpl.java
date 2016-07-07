/**
 * 
 */
package com.ravi.bank.domain.security.service.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ravi.bank.domain.security.service.jpa.entities.Authority;
import com.ravi.bank.domain.security.service.jpa.entities.User;

/**
 * @author Ravi Aghera
 *
 */
@Repository(value="securityDao")
public class SecurityDaoImpl implements SecurityDao {

	@PersistenceContext
	@Autowired
	EntityManager em;
	
	final private static Logger logger =  LoggerFactory.getLogger(SecurityDaoImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findUserById(Long id) {
		final User result = em.find(User.class, id);
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addNewUser(User user) {
		
		em.persist(user);

		//Rethink this - do you really want business rules in the Dao??  Probably not.
		//Better to have things in the Domain objects or Service layer and being explicit
//		List<Authority> authList = user.getAuthorityList();
//		if(authList != null) {
//			for (Authority authority : authList) {
//				//Authority MUST have a User
//				if(authority.getUser() == null) {
//					authority.setUser(user);
//				}
//				em.persist(authority);
//			}
//		}

		//return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addNewUserAndAssociatedAuthorityList(User user) {
		
		em.persist(user);
		
		List<Authority> authList = user.getAuthorityList();
		if(authList != null) {
			for (Authority authority : authList) {
				//Authority MUST have a User
				if(authority.getUser() == null) {
					authority.setUser(user);
				}
				em.persist(authority);
			}
		}

		//return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteUser(User user) {
		
		em.remove(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteUserById(Long id) {
		User user = em.find(User.class, id);
		em.remove(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User updateUser(User user) {
		em.merge(user);
		return user;
	}
	
	/** {@inheritDoc} */
	@Override
	public void addNewAuthority(Authority authority) {
		em.persist(authority);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAuthority(Authority authority) {
		em.remove(authority);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAuthorityById(Long id) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Authority findAuthorityById(Long id) {
		final Authority result = em.find(Authority.class, id);
		return result ;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Authority updateAuthority(Authority authority) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * {@inheritDoc}
	 */
	//private List<Authority> getAllAuthoritiesByUserId(User user) {
		
		//TODO JPA Select query:
		//Select * from Authorities where user_id = ?; (user.getUserId())
		
	//}

}
