/**
 * 
 */
package com.ravi.spring.jdbc;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ravi.bank.domain.service.jpa.entities.TransactionType;


/**
 * Test for the JdbcService
 * 
 * @author Ravi Aghera
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:spring-all-context.xml" })
public class JdbcServiceTest{

	@Autowired
	ApplicationContext context;
	
	@Autowired
	JdbcService jdbcService;
	
	final private static Logger logger =  LoggerFactory.getLogger(JdbcServiceTest.class);
	
	@Test
	public void testJdbcConnection() {
		
		//final JdbcService service = (JdbcService) context.getBean("jdbcService");
		Assert.assertNotNull("Not getting the jdbcService instance", jdbcService);
		
		List<TransactionType> result = jdbcService.getAllTransactionTypes();
		Assert.assertNotNull(result);
		logger.info( "Got some results .... {} ", result);
		Assert.assertEquals("Results size not correct. ", 2, result.size());
		
		for (TransactionType transactionType : result) {
			
			logger.info( "Result: {}", transactionType.toString() );
			
			Assert.assertNotNull("TransactionType is null.", transactionType);
			//Assert.assertEquals("TypeName is incorrect.", "CREDIT", transactionType.getTransactionTypeName());
			
		}
		
	}
	
	
}
