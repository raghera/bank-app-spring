/**
 * 
 */
package com.ravi.spring.jdbc;	

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.ravi.bank.domain.service.jpa.entities.TransactionType;

import junit.framework.TestCase;


/**
 * A sanity check for the DB
 * 
 * @author Ravi Aghera
 *
 */
public class TestDbSanityCheck extends TestCase {

	
	@Test
	public void testBasicSelect() throws Exception {
		 
		BasicDbTest dbRunner = new BasicDbTest();
		List<TransactionType> transTypeList = dbRunner.runQuery();

		assertNotNull(transTypeList);
		assertEquals(2, transTypeList.size());
		
		for (TransactionType transactionType : transTypeList) {
			assertNotNull(transactionType.getTransactionTypeId());
			assertNotNull(transactionType.getTransactionTypeName());
			
		
			if(transactionType.getTransactionTypeId() == 1) {
				assertEquals("CREDIT",transactionType.getTransactionTypeName());
			}
			else {
				assertEquals("DEBIT",transactionType.getTransactionTypeName());
			}
			
		}
		
	}
	
	
}
