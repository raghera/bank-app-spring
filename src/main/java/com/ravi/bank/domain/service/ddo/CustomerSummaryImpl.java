/**
 * 
 */
package com.ravi.bank.domain.service.ddo;

import com.ravi.bank.domain.service.jpa.entities.Customer;
import com.ravi.bank.domain.service.jpa.entities.CustomerImpl;

/**
 * A Domain Data object used to transfer required data to
 * clients.
 * 
 * Uses the builder pattern for easy creation.
 * 
 * Only getter methods are exposed after creation.
 * @author Ravi Aghera
 *
 */
public class CustomerSummaryImpl implements CustomerSummary{

	private Long customerId;
	private Long customerType;
	private String firstName;
	private String surname;
	private String email;
	private String countryCode;
	
	public CustomerSummaryImpl(Customer customer) {
		
		validateCustomerArg(customer);
		
		this.customerId = customer.getCustomerId();
		this.customerType = customer.getCustomerType();
		this.firstName = customer.getFirstName();
		this.surname = customer.getSurname();
		this.email = this.getEmail();
		this.countryCode = this.getCountryCode();
		
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getCustomerType() {
		return customerType;
	}
	public void setCustomerType(Long customerType) {
		this.customerType = customerType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	private boolean validateCustomerArg(Customer customer) {
		
		if(null == customer.getCustomerId()) {
			throw new IllegalArgumentException("Customer should always have an id");
		}
		//add more validation here.
		
		return true;
		
	}
	
}
