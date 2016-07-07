/**
 * 
 */
package com.ravi.bank.domain.dao;

import java.util.Date;

/**
 * Super class to DaoDBIntegration tests.
 * Contains useful methods required in common tasks.
 * 
 * @author Ravi Aghera
 *
 */
public class TestDaoDbIntegration {

	/**
	 * Creates a random new e-mail address so
	 * we don't get unique constraint errors.
	 *  
	 * @return email string
	 */
	protected String generateNewEmail() {
		return "test.junit@" + new Date().getTime() + ".com";
	}
	
}
