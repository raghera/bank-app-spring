/**
 * 
 */
package com.ravi.bank.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ravi.bank.domain.service.dao.AccountDao;
import com.ravi.bank.domain.service.jpa.entities.Account;

/**
 * @author Ravi Aghera
 *
 */
@Component("accountService")
public class AccountDomainServiceImpl implements AccountDomainService {

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public Account getAccountWithTransactions(Long accountId) {
		Account account = accountDao.findAccountByIdWithTransactions(accountId);
		return account;
	}

}
