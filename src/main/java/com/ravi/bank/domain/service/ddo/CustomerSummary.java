/**
 * 
 */
package com.ravi.bank.domain.service.ddo;

/**
 * This exposes getter methods to provide pertinent
 * data about a Customer object required for clients.
 * 
 * @author Ravi Aghera
 *
 */
public interface CustomerSummary {

	public Long getCustomerId(); 
	public void setCustomerId(Long customerId);
	public Long getCustomerType();
	public void setCustomerType(Long customerType);
	public String getFirstName();
	public void setFirstName(String firstName);
	public String getSurname();
	public void setSurname(String surname);
	public String getEmail();
	public void setEmail(String email);
	public String getCountryCode();
	public void setCountryCode(String countryCode);
	
}
