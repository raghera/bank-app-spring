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
public class CustomerTypeImpl implements CustomerType {
	
	private int customerTypeId;
	private String customerTypeName;
	
	@Override
	public int getCustomerTypeId() {
		return customerTypeId;
	}
	
	@Override
	public void setCustomerTypeId(int customerTypeId) {
		this.customerTypeId = customerTypeId;
	}
	
	@Override
	public String getCustomerTypeName() {
		return customerTypeName;
	}
	
	@Override
	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}
	
}
