/**
 * 
 */
package com.ravi.spring.hello;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ravi.bank.domain.service.jpa.entities.TransactionType;
import com.ravi.spring.jdbc.JdbcService;

/**
 * During development we need a MainApplication to run
 * to kick things off so we can see things working.
 * 
 * In the end the web application will make it happen.
 * @author Ravi Aghera
 *
 */
public class MainApplication {

	public static void main(String[] args) {
		
		//load the Spring IoC container by loading some beans
		final ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		final JdbcService service = (JdbcService) context.getBean("jdbcService");
		
		List<TransactionType> result = service.getAllTransactionTypes();
		
	}

}
