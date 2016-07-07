package com.ravi.bank.domain.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import oracle.jdbc.OracleResultSetMetaData.SecurityAttribute;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ravi.bank.domain.security.service.dao.SecurityDao;
import com.ravi.bank.domain.security.service.jpa.entities.User;
import com.ravi.bank.domain.security.service.jpa.entities.Authority;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:spring-all-context.xml" })
public class TestSecurityDbIntegration {
	
	final private static Logger logger =  LoggerFactory.getLogger(TestSecurityDbIntegration.class);
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	SecurityDao securityDao;
	
	private Authority auth;
	private Authority auth2;
	private List<Authority> authList;
	private User user;
	private String username;
	
	@Before
	public void setUp() {
		authList = new ArrayList<Authority>();
		username = "User" + new Date().getTime();
		
		auth = new Authority();
		auth.setAuthority("ROLE_USER");
		auth2 = new Authority();
		auth2.setAuthority("ROLE_ADMIN");
		
		authList.add(auth);
		authList.add(auth2);

		user = new User();
		user.setEnabled(true);
		user.setPassword("password");
		user.setUserName(username);
		user.setAuthorityList(authList);

	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCRUDUserAndAssociatedAuthorities() {
		
		//Check ids are null to start
		assertNull(user.getUserId());
		assertNull(auth.getId());
		assertNull(auth2.getId());
		
		String newRole = "NEW_ROLE";
		securityDao.addNewUserAndAssociatedAuthorityList(user);
		
		//Check id's created and peristed.
		Long authId = auth.getId();
		Long authId2 = auth2.getId();
		Long userId = user.getUserId();
		assertNotNull("Authority not persisted correctly.  Should have an id.", authId);
		assertNotNull("Authority not persisted correctly.  Should have an id.", authId2);
		assertNotNull("User not persisted correctly.  Should have an id.", userId);
		
		//test find and check
		User addedUser = securityDao.findUserById(userId);
		assertNotNull(addedUser);
		assertNotNull(addedUser.getAuthorityList());
		assertEquals(user.getUserName(), addedUser.getUserName());
		assertEquals(user.getUserId(), addedUser.getUserId());
		assertEquals(authList.size(), addedUser.getAuthorityList().size());

		//Match the Auths
		for (Authority authority : addedUser.getAuthorityList()) {
			String authName = authority.getAuthority();
			assertNotNull(authName);
			assertTrue("Authority: " + authName + " is incorrect.", authName.equals(auth.getAuthority()) || authName.equals(auth2.getAuthority()));
		}
		
		//Test Update
		
		//Change user and authority
		addedUser.setPassword("welcome");
		List<Authority> addedList = addedUser.getAuthorityList();
		//Remove this from List within User, but should not be removed from DB.
		Authority authToRemove = addedList.get(1);
		logger.debug("Removing authority: {}", addedList.get(1).getAuthority() );
		Long removedAuthId = addedList.get(1).getId();
		addedList.remove(1);//This should NOT be removed from the DB.  An explicit remove operation is required.
		addedList.get(0).setAuthority(newRole); //This should be added to persistent storage.
		
		assertEquals("Size of list incorrect", 1, addedList.size());
		
		logger.info("Authority set: {}.", addedList.get(0).getAuthority() );
		addedUser.setAuthorityList(addedList); //should be the same anyway.
		
		Long addedId = addedUser.getUserId();
		securityDao.updateUser(addedUser);
		
		//Find and check
		User updatedUser = securityDao.findUserById(addedId);
		assertNotNull( updatedUser.getPassword() );
		assertEquals( addedUser.getPassword(), updatedUser.getPassword());
		List<Authority> auths = updatedUser.getAuthorityList();
		assertNotNull(auths);
		assertEquals(1, auths.size());
		assertNotNull( auths.get(0) );
		assertNotNull( auths.get(0).getAuthority() );
		assertEquals( addedList.get(0).getAuthority(), auths.get(0).getAuthority() );
		
		//Check the Authority from inside User has not been removed. 
		Authority removedAuth = securityDao.findAuthorityById(removedAuthId);
		assertNotNull("Removed Authority when it should not be: " + removedAuthId, removedAuth);
		logger.info("Removed Authority: {}.", removedAuth.getAuthority() );
		assertEquals(removedAuth.getAuthority(), authToRemove.getAuthority());

		//Remove Authorities and then User
		securityDao.deleteAuthority(auth);
		securityDao.deleteAuthority(auth2);
		securityDao.deleteUser(updatedUser);
		
		//Check they're gone
		Authority deletedAuth = securityDao.findAuthorityById(authId);
		Authority deletedAuth2 = securityDao.findAuthorityById(authId2);
		User deletedUser = securityDao.findUserById(userId);
		assertNull(deletedAuth);
		assertNull(deletedAuth2);
		assertNull(deletedUser);
		
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void testSimpleCRUDUser() {
		
		assertNull(user.getUserId());
		securityDao.addNewUser(user);
		
		//Check id's created and peristed.
		Long userId = user.getUserId();
		Long authId = auth.getId(); //Should still be null and not peristed!
		Long authId2 = auth2.getId();
		assertNotNull("User not persisted correctly.  Should have an id.", userId);
		assertNull("Authority should not be persisted.", authId);
		assertNull("Authority should not be persisted.", authId2);
		
		//test find and check
		User addedUser = securityDao.findUserById(userId);
		assertNotNull(addedUser);

		assertEquals(user.getUserName(), addedUser.getUserName());
		assertEquals(user.getUserId(), addedUser.getUserId());
		assertEquals(authList.size(), addedUser.getAuthorityList().size());

		assertNotNull("AuthorityList should not have been persisted.  No explicit call to do so.", 
				addedUser.getAuthorityList()); //Not persisted so should be empty.

		//Test update
		addedUser.setPassword("welcome");
		
		Long addedId = addedUser.getUserId();
		
		/*
		 * Either you persist the AuthorityList here or you remove them, as here.
		 * Otherwise Hibernate throws a TransientObjectException and Spring wraps it as a 
		 * InvalidDataAccessApiUsageException
		 * See testTransientException
		 */
		addedUser.setAuthorityList(new ArrayList<Authority>());
		
		securityDao.updateUser(addedUser);	
		
		User updatedUser = securityDao.findUserById(addedId);
		assertNotNull( updatedUser.getPassword() );
		assertEquals( addedUser.getPassword(), updatedUser.getPassword());

		//Test delete
		securityDao.deleteUser(updatedUser);
		User deletedUser = securityDao.findUserById(userId);
		
		assertNull(deletedUser);
		
		
	}
	
	public void testSimpleCRUDAuthority() {
		String newRole = "NEW_ROLE";
		assertNull(auth.getId());
		assertNull(auth2.getId());
		
		Long authId = auth.getId();
		Long authId2 = auth2.getId();
		assertNotNull("Authority not persisted correctly.  Should have an id.", authId);
		assertNotNull("Authority not persisted correctly.  Should have an id.", authId2);

//		List<Authority> addedList = addedUser.getAuthorityList();
//		//Remove this from List within User, but should not be removed from DB.
//		Authority authToRemove = addedList.get(1);
//		logger.debug("Removing authority: {}", addedList.get(1).getAuthority() );
//		Long removedAuthId = addedList.get(1).getId();
//		addedList.remove(1);//This should NOT be removed from the DB.  An explicit remove operation is required.
//		addedList.get(0).setAuthority(newRole); //This should be added to persistent storage.
//		
//		assertEquals("Size of list incorrect", 1, addedList.size());

		
	}
	
	/**
	 * JPA does not allow you to have a Transient object.  Attempt to do this and check
	 * that Spring wraps it in a InvalidDataAccessApiUsageException and marks it for Rollback
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void testJpaHibernateTransientException() {

		assertNull(user.getUserId());
		securityDao.addNewUser(user);
		
		/*	Update with User containing AuthList (throws Exception since
		 *	Authority is in a Transient state - not been persisted explicitly
		 *	Either remove or persist it too.
		 */
		boolean thrown = false;
		try {
			securityDao.updateUser(user);	
		} catch(InvalidDataAccessApiUsageException e) {
			thrown = true;
			logger.debug("Exception thrown", e);
		}
		if(!thrown){
			fail("Should have had an Illegal state Exception thrown, for a Transient object in the Entity (Authorities in User)");
		}
		
	}

	@Test
	@Transactional
	@Rollback(false)
	public void testInsertEntitiesSeparateAndIdsMatch() {
		assertNull(user.getUserId());
		securityDao.addNewUser(user);
		
		//Now persist the Authority objects
		securityDao.addNewAuthority(auth);
		securityDao.addNewAuthority(auth2);
		
		assertNotNull(auth);
		assertNotNull(auth.getUser());
		assertNotNull(auth.getUser().getUserId());
		assertEquals(user.getUserId(), auth.getUser().getUserId());
		
		assertNotNull(auth2);
		assertNotNull(auth2.getUser());
		assertNotNull(auth2.getUser().getUserId());
		assertEquals(user.getUserId(), auth2.getUser().getUserId());		
		
		//Both now have ids
		assertNotNull(auth.getId());
		assertNotNull(auth2.getId());
		
		user.setPassword("welcome");
		securityDao.updateUser(user);
		
		assertEquals("welcome", user.getPassword());

	}
	
	
	
}
