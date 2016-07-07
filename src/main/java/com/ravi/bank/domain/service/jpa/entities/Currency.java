/**
 * 
 */
package com.ravi.bank.domain.service.jpa.entities;

/**
 * A Data Domain Object for the Currency database table
 * 
 * @author Ravi Aghera
 *
 */
public interface Currency {
	
	/**
	 * Getter
	 * @return - the currencyId field
	 */
	public int getCurrencyId();
	
	/**
	 * Setter
	 * @param currencyId - the value to be set
	 */
	public void setCurrencyId(int currencyId);
	
	/**
	 * Getter
	 * @return - the currencyName field
	 */
	public String getCurrencyName();
	
	/**
	 * Setter
	 * @param currencyName - the value to be set
	 */
	public void setCurrencyName(String currencyName);
	
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
	 * @return - the currencyCode field
	 */
	public String getCurrencyCode();
	
	/**
	 * Setter
	 * @param currencyCode - the value to be set
	 */
	public void setCurrencyCode(String currencyCode); 

}
