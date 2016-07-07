/**
 * 
 */
package com.ravi.domain.service.web;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RunAs;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ravi.bank.BankService;
import com.ravi.bank.domain.service.jpa.entities.Customer;
import com.ravi.bank.domain.service.jpa.entities.CustomerImpl;
import com.ravi.bank.web.HomeController;





/**
 * Unit test for the HomeController MVC Controller
 * 
 * @author Ravi Aghera
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-all-context.xml")
public class TestHomeController {

	@Mock BankService bankService;
	
	@Before
	public void runInit() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	@Ignore
	public void testDisplayNewestCustomer() {
		
		//Object for Mock to return
		Customer cust = new CustomerImpl();
		cust.setCustomerId(1L);
		
		//List<Customer> expectedCustList = Arrays.asList( cust );
		//BankService bankService = Mockito.mock(BankService.class);
		
		//Expectations
		Mockito.when(bankService.getNewestCustomerDetails()).thenReturn( cust ) ;
		
		//Test
		HomeController controller = new HomeController(bankService);
		Map<String, Object> model = new HashMap<String, Object>();
		String viewName = controller.showHomePage(model);
		
		//Verify
		assertEquals("home", viewName);
		assertSame(cust, model.get("newCustomer")); //Same means the same object in memory not equals().
		Mockito.verify(bankService).getNewestCustomerDetails(); //Checks that the operation specified happened once.
	}
	
}
