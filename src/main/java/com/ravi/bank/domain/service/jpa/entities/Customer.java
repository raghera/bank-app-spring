/**
 * 
 */
package com.ravi.bank.domain.service.jpa.entities;

import java.util.List;

/**
 * A Data Domain Object for the Customer database table
 * 
 * @author Ravi Aghera
 *
 */
public interface Customer {
	
	/**
	 * Getter
	 * @return - the customerId field
	 */
	public Long getCustomerId(); 
	
	/**
	 * Setter
	 * @param customerId - the value to be set
	 */
	public void setCustomerId(Long customerId);
	
	/**
	 * Getter
	 * @return - the customerType field
	 */
	public Long getCustomerType();
	
	/**
	 * Setter
	 * @param customerType - the transactionId field
	 */
	public void setCustomerType(Long customerType);
	
	/**
	 * Getter
	 * @return - the firstName field
	 */
	public String getFirstName();
	
	/**
	 * Setter
	 * @param firstName - the value to be set
	 */
	public void setFirstName(String firstName);
	
	/**
	 * Getter
	 * @return - the surname field
	 */
	public String getSurname();
	
	/**
	 * Setter
	 * @param surname - the value to be set
	 */
	public void setSurname(String surname);
	
	/**
	 * Getter
	 * @return - the email field
	 */
	public String getEmail();
	
	/**
	 * Setter
	 * @param email - the value to be set
	 */
	public void setEmail(String email);
	
	/**
	 * Getter
	 * @return - the countryCode field
	 */
	public String getCountryCode();
	
	/**
	 * Setter
	 * @param countryCode - the value to be set
	 */
	public void setCountryCode(String countryCode);

	/**
	 * Getter
	 * @return - the accountsList field
	 */
	public List<Account> getAccountsList();

	/**
	 * Setter
	 * @param accountsList - the value to be set
	 */
	public void setAccountsList(List<Account> accountsList);
	
	public String getPassword();
	
	public void setPassword(String password); 

}
