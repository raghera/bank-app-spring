/**
 * 
 */
package com.ravi.bank.domain.service.jpa.entities;


/**
 * @author Ravi Aghera
 *
 */
public class CurrencyImpl implements Currency {

	private int currencyId;
	private String currencyName;
	private String countryCode;
	private String currencyCode;

	@Override
	public int getCurrencyId() {
		return currencyId;
	}
	
	@Override
	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}
	
	@Override
	public String getCurrencyName() {
		return currencyName;
	}
	
	@Override
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	
	@Override
	public String getCountryCode() {
		return countryCode;
	}
	
	@Override
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	@Override
	public String getCurrencyCode() {
		return currencyCode;
	}
	
	@Override
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
}
