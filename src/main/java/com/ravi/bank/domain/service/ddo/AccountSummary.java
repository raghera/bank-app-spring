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
public interface AccountSummary {

	public Long getAccountId();
	public void setAccountId(Long accountId);
	
	public Double getBalance();
	public void setBalance(Double balance);
	
	public String getStatus();
	public void setStatus(String status);
	
	public Long getCustomerId();
	public void setCustomerId(Long id);
	
	public String getAccountType();
	public void setAccountType(String type);
	
	public Integer getCurrencyCode();
	public void setCurrencyCode(Integer code);

}
