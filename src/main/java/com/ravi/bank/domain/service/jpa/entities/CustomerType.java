/**
 * 
 */
package com.ravi.bank.domain.service.jpa.entities;

/**
 * A Data Domain Object for the Customer_Type database table
 * 
 * @author Ravi Aghera
 *
 */
public interface CustomerType {

	/**
	 * Getter
	 * @return - the customerTypeId field
	 */
	public int getCustomerTypeId();

	/**
	 * Setter
	 * @param customerTypeId - the value to be set
	 */
	public void setCustomerTypeId(int customerTypeId);
	
	/**
	 * Getter
	 * @return - the customerTypeName field
	 */
	public String getCustomerTypeName();
	
	/**
	 * Setter
	 * @param customerTypeName - the value to be set
	 */
	public void setCustomerTypeName(String customerTypeName);

}
