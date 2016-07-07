/**
 * 
 */
package com.ravi.bank.domain.service.ddo;

import com.ravi.bank.domain.service.jpa.entities.Account;

/**
 * Data Bean which holds domain data without any attachment to the DB or JPA.
 * This is used to pass the data to other tiers - particularly the web tier.
 * 
 * Optional use of Builder pattern here to create this object using the
 * enclosed type AccountSummaryBuilder.
 * 
 * In most cases the constructor using the Account Entity object should suffice.
 * 
 * @author Ravi Aghera
 *
 */
public class AccountSummaryImpl implements AccountSummary{

	private Long accountId;
	private Double outBalance;
	private String status;
	private Long customerId;
	private String accountType;
	private Integer currencyCode;
	
	//Used only by the Builder
	private AccountSummaryImpl(AccountSummaryBuilder builder) {
		this.accountId = builder.accountId;
		this.outBalance = builder.balance;
		this.status = builder.status;
		this.customerId = builder.customerId;
		this.accountType = builder.accountType;
		this.currencyCode = builder.currencyCode;
	}

	//In most cases this is what is required.
	public AccountSummaryImpl(Account account) {
		
		if(validAccountArguments(account))
		
		this.accountId = account.getAccountId();
		this.outBalance = account.getBalance();
		this.status = account.getStatus().getName();
		this.customerId = account.getCustomer().getCustomerId();
		this.accountType = account.getAccountType().getName();
		this.currencyCode = account.getCurrencyId();
	}
	
	@Override
	public Long getAccountId() {
		return accountId;
	}
	
	@Override
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	@Override
	public Double getBalance() {
		return outBalance;
	}
	
	@Override
	public void setBalance(Double balance) {
		this.outBalance = balance;
	}
	
	@Override
	public String getStatus() {
		return status;
	}
	
	@Override
	public void setStatus(String accountStatus) {
		this.status = accountStatus;
	}
	
	@Override
	public Long getCustomerId() {
		return customerId;
	}
	@Override
	public void setCustomerId(Long id) {
		this.customerId = id;
	}
	
	@Override
	public String getAccountType() {
		return accountType;
	}
	@Override
	public void setAccountType(String type) {
		this.accountType = type;
	}
	
	@Override
	public Integer getCurrencyCode() {
		return currencyCode;
	}
	
	@Override
	public void setCurrencyCode(Integer code) {
		this.currencyCode = code;		
	}
	
	
	private boolean validAccountArguments(Account account) {
		boolean success = true;
		
		if(null == account.getCustomer() && null == account.getCustomer().getCustomerId()) {
			success = false;
		}
		
		return success;
		
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountSummaryImpl [accountId=").append(accountId)
				.append(", balance=").append(outBalance).append(", status=")
				.append(status).append(", customerId=").append(customerId)
				.append(", accountType=").append(accountType)
				.append(", currencyCode=").append(currencyCode).append("]");
		return builder.toString();
	}


	public static class AccountSummaryBuilder {
		
		private Long accountId;
		private Double balance;
		private String status;
		private Long customerId;
		private String accountType;
		private Integer currencyCode;
		
		//Mandatory fields
		public AccountSummaryBuilder(Long accountId, Double balance,
				Long customerId, Integer currencyCode) {
			this.accountId = accountId;
			this.balance = balance;
			this.customerId = customerId;
			this.currencyCode = currencyCode;
			
		}
		
		public AccountSummaryBuilder accountId(Long accountId) {
			this.accountId = accountId; 
			return this;
		}
		
		public AccountSummaryBuilder balance(Double balance) {
			this.balance = balance;
			return this;
		}
		
		public AccountSummaryBuilder accountStatus(String accountStatus) {
			this.status = accountStatus;
			return this;
		}		
		public AccountSummaryBuilder customerId(Long customerId) {
			this.customerId = customerId;
			return this;
		}
		
		public AccountSummaryBuilder accountName(String accountName) {
			this.accountType = accountName;
			return this;
		}
		
		public AccountSummaryBuilder currencyCode(Integer currencyCode) {
			this.currencyCode = currencyCode;
			return this;
		}
		
		public AccountSummary build() {
			AccountSummary accountSummary = new AccountSummaryImpl(this);
			validate(accountSummary);
			return accountSummary;
		}
		
		private boolean validate(AccountSummary accSummary) {
			if(accSummary.getCustomerId() == null) {
				return false;
			}
			return true;
		}
		
	}
}
