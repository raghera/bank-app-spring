/**
 * 
 */
package com.ravi.bank.web;

import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ravi.bank.BankService;
import com.ravi.bank.domain.service.jpa.entities.Customer;

/**
 * Controller for the Home page of Bank app.
 * 
 * @author Ravi Aghera
 *
 */
@Controller
public class HomeController {
	
	@Autowired
	private BankService bankService;
	
	public HomeController() {
	}
	
	@Inject
	public HomeController(BankService bankService) {
		this.bankService = bankService;
	}
	
	@RequestMapping(value={"/","/home"})
	public String showHomePage(Map<String, Object> model) {
		
		//Name of the view to go to next i.e. homepage
		String viewName = "home";
		//Customer returned = bankService.getNewestCustomerDetails();
		//model.put( "newCustomer", bankService.getNewestCustomerDetails() );
		
		return viewName;
	}
	
	@RequestMapping(value={"/home"}, params="logout")
	public String logout(Map<String, Object> model) {
		
		//Name of the view to go to next i.e. homepage
		String viewName = "home";
		//Customer returned = bankService.getNewestCustomerDetails();
		model.put( "logoutMessage", "You're logged out." );
		
		return viewName;
	}
	
	
	/**
	 * If it's a failure then we will get auth=fail as a parameter.
	 * So we have a controller to add any specific messages.  Could add a ResourceBundle here as messages.
	 * 
	 * - Don't need to set the messages here but keeps your jsp cleaner.
	 * @param auth
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/home", method=RequestMethod.GET, params="auth" )
	public String loginFail(@RequestParam(value="auth", required=false) String auth , Model model,  HttpSession session, Principal principal) {
		//getRoles(principal);
		//Add some attributes we can use in the front end.  
		//Note that the param auth will still be available as we are in the same request
		model.addAttribute("loginError", "fail");
		//Set a message parameter
		model.addAttribute("loginErrorMessage", 
				"Sorry, your username and/or password were incorrect.  Please try again or register.");
		
		Enumeration<String> e = session.getAttributeNames();
		
		while(e.hasMoreElements()) {
			System.out.println("AttrName: " + e.nextElement());
		}
		
//		if(session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") instanceof Exception) {
//		
//			Exception ex = (Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
//			
//			System.out.println("Ex.message: " + ex.getMessage() );
//			System.out.println("Ex: " + ex );
//		}
		
		//Return to home and display the appropriate messages set above.
		return "home";
		
	}
	
	/*
	 * ??? Only finds this in HomeController and not CustomerController ???
	 * WHY?
	 */
	/**
	 * This is the default success login page mapping.
	 * We want to display the Account details page.
	 */
	@RequestMapping("/customer/login")
	public String loginSuccess(Model model, Principal principal) {
		
		//TODO Remove
		String roles = getRoles(principal);
		
		System.out.println("User principle: " + principal);
		final Customer customerDetails = bankService.getCustomerDetails(principal.getName());
		
		if(null == customerDetails) {
			String email = principal.getName();
			String message = "Customer could not be found with the e-mail address: " + email + ". Please check or register as a new user.";
			model.addAttribute("message", message);
			return "customer/customernotfound";
		}
		
		String welcomeMessage = "Welcome " + customerDetails.getFirstName() +".  Please see your account details below.";
		
		model.addAttribute(customerDetails);//Actually sets the key as customerImpl - the name of the type in camelCase
		model.addAttribute("greeting", welcomeMessage);
		
		return "customer/accounts/list";
		
	}
	
	/**
	 * Simple website contact Page.
	 * 
	 */
	@RequestMapping(value="/contact")
	public String contact() {
	
		return "contact";
		
	}
	
	

	/*
	 * Put in a ControllerUtility Class
	 */
	private String getRoles(Principal principal) {
		
		StringBuilder sb = new StringBuilder();
		
		User activeUser = (User) ((Authentication) principal).getPrincipal();
		Collection<GrantedAuthority> auths = activeUser.getAuthorities();
		
		for (Iterator<GrantedAuthority> iterator = auths.iterator(); iterator.hasNext();) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) iterator.next();
			
			sb.append(grantedAuthority + ", ");
			
			System.out.println("Authorities retrieved: " + grantedAuthority.getAuthority());
		}
		
		return sb.toString();
	}
	
	
}
