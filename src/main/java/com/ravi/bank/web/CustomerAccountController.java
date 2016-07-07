/**
 * 
 */
package com.ravi.bank.web;


import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ravi.bank.BankService;
import com.ravi.bank.domain.service.jpa.entities.Account;
import com.ravi.bank.domain.service.jpa.entities.Customer;
import com.ravi.bank.domain.service.jpa.entities.CustomerImpl;
import com.ravi.bank.domain.service.jpa.entities.Transaction;

/**
 * Controller for requests from a Customer
 * from the Front End
 * 
 * @author Ravi Aghera
 *
 */
@Controller
@RequestMapping("/customer")
public class CustomerAccountController {
	
	@Inject
	private BankService bankService;
	
	/*
	 * This one uses @RequestParameter rather than @PathVariable (which is more RESTful
	 * This is standard get notation - ?email=raghera@hotmail.com rather than /{raghera@hotmail.com}
	 */
	@RequestMapping(value="/accounts", method=RequestMethod.GET)
	public String getCustomerAccountList(
			@RequestParam("email") String email, Model model) {

		
		
		//Get the customer and account details.
		final Customer customerDetails = bankService.getCustomerDetails(email);
		
		if(null == customerDetails) {
			//TODO - load this from a ResourceBundle
			final String message = "Customer could not be found with the e-mail address: " + email + ". Please check or register as a new user.";
			model.addAttribute("message", message);
			
			return "customer/customernotfound";
		}
		
		model.addAttribute(customerDetails);//Actually sets the key as customerImpl
		
		return "customer/accounts/list";
	}
	
	//Direct to create customer form
	@RequestMapping(value="/edit", method=RequestMethod.GET, params="new")
	public String createCustomerWithForm(Model model){
		
		model.addAttribute(new CustomerImpl());
			
		return "customer/edit";
		
	}
	
	/** 
	 * Processes a submitted new Customer form
	 */
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String addCustomerFromForm(@Valid CustomerImpl customer, BindingResult bindingResult) {
		/*
		 * Note how the CustomerImpl implementation is required here as an argument not the Interface type.  
		 * This is because this method processes a form submission.  Once submitted the fields in the request 
		 * will be bound to the Object that's passed in as an argument here.  So Spring attempts to 
		 * instantiate this object and set it's values.  If it is an Interface it won't be able to instantiate it and so
		 * you'll get some kind of BeanFactory context exception at runtime.
		 */
		
		if(bindingResult.hasErrors()) {
			return "customer/edit";
		}
		
		//TODO Change add customer type as pre-populated drop down value.
		customer.setCustomerType(1L);
		final Long id = bankService.createNewCustomer(customer);
		//TODO insert new userId into sec db
		//model.addAttribute("customerId", id);//not used but seems to add it to the request parameter
		
		return "redirect:/customer/edit/" + customer.getEmail();
		
	}
	
	/**
	 * Controls display of customer details after creation 
	 */
	@RequestMapping(value="/edit/{email:.+}", method=RequestMethod.GET)
	public String showCustomerRecord(@PathVariable String email, Model model) {
		
		/*
		 * By default Spring mvc assumes anything after the .(dot) is a suffix denoting  
		 * a file extension.  E.g. fileName.json / .xml etc.  So it hacks it off.
		 * The proper way to deal with this in Spring 3.2 is here:
		 * http://stackoverflow.com/questions/16332092/spring-mvc-pathvariable-with-dot-is-getting-truncated
		 * In Spring 4 you can add attributes to annotation-driven xml namespace instead.
		 * 
		 * Most however did not work so used the :.+ regex which does the trick.  We expect only e-mail addresses here.
		 */
		
		final Customer customer = bankService.getCustomerDetails(email);
		model.addAttribute(customer);
		
		return "customer/accounts/list";
	}
	
	/**
	 * Access Denied mapping.
	 * If a user logs in correctly but does not have sufficient privileges
	 * (or correct role) to access the page they are after.
	 */
	@RequestMapping(value="/login/denied403")
	public String handleAccessDenied(Principal principal, Model model){
		String message;
		
		//TODO Remove
		getRoles(principal);
		
		if(principal != null) {
			message = "The username/e-mail: " + principal.getName() + " does not have sufficient permissions to access this page."
					+ "  Please contact the administrator.";
		}
		else {
			message = "You do not have the permissions to access this page.  Please contact the administrator.";
		}
		
		model.addAttribute("message", message);
		
		return "denied403";
		
	}
	
	/**
	 * Display the Transactions for a particular AccountId
	 */
	@RequestMapping(value="/accounts/transactions/{accountId}", method=RequestMethod.GET)
	public String showTransactionsForAccount( @PathVariable Long accountId, Model model, Principal principal ) {
		
		//TODO Remove
		getRoles(principal);
		
		System.out.print(accountId);
		
		final Account account = bankService.getAccountWithTransactions(accountId);
		model.addAttribute("account", account);
		model.addAttribute("transactionList", account.getTransactionList());
		model.addAttribute("email", principal.getName());
		
		
		return "customer/account/transactions";
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
