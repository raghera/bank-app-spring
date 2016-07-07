/**
 * 
 */
package com.ravi.bank.domain.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ravi.bank.domain.security.service.dao.SecurityDao;
import com.ravi.bank.domain.security.service.jpa.entities.Authority;
import com.ravi.bank.domain.security.service.jpa.entities.User;
import com.ravi.bank.domain.service.jpa.entities.Customer;

/**
 * @author Ravi Aghera
 *
 */
@Component("securityService")
@Transactional
public class SecurityManagementServiceImpl implements SecurityManagementService {

	@Autowired
	private SecurityDao securityDao;
	
	@Override
	public User addUser(Customer customer) {
		
		User user = new User(customer);
		securityDao.addNewUser(user);

		return user;
	}
	
	@Override
	public User addUserAndAuthority(Customer customer) {

		User user = new User(customer);
		securityDao.addNewUser(user);
		
		Authority authority = new Authority();
		//TODO - Change this so it's passed in
		List<Authority> aList = new ArrayList<Authority>();
		authority.setAuthority("ROLE_USER");
		aList.add(authority);
		
		user.setAuthorityList(aList);
		securityDao.addNewAuthority(authority);
		
		
		return user; 
	}

	@Override
	public void removeUser(User user) {
		securityDao.deleteUser(user);
	}

	@Override
	public void removeUserAndAuthorities(User user) {
		
		List<Authority> authList = user.getAuthorityList();
		
		for (Authority authority : authList) {
			securityDao.deleteAuthority(authority);
		}
		
		securityDao.deleteUser(user);
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void modifyUser(User user) {
		throw new UnsupportedOperationException();
	}

}
