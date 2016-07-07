/**
 * 
 */
package com.ravi.bank.domain.service;

import com.ravi.bank.domain.service.ddo.AccountSummary;
import com.ravi.bank.domain.service.ddo.CustomerSummary;
import com.ravi.bank.domain.service.ddo.TransactionSummary;
import com.ravi.bank.domain.service.jpa.entities.Account;
import com.ravi.bank.domain.service.jpa.entities.Customer;

/**
 * @author Ravi Aghera
 *
 */
public class ConversionServiceImpl implements ConversionService {

	@Override
	public AccountSummary convertAccountEntity(Account account) {
		
		return null;
	}

	@Override
	public CustomerSummary convertCustomerEntity(Customer customer) {
		return null;
	}

	@Override
	public TransactionSummary convertCustomerEntity(TransactionSummary transaction) {
		return null;
	}

	
	
}
