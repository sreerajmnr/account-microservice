package com.banking.core.service;

import java.util.Optional;

import com.banking.core.model.dto.Account;
import com.banking.core.model.dto.response.AccountResponse;

public interface AccountService {

	void createAccount(Optional<Account> account);

	AccountResponse getAccount(String accountNumber);

	void updateAccount(Optional<Account> account);

	void updateAccountBalance(Optional<Account> account);

}
