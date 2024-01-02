package com.banking.core.service;

import java.util.Optional;

import com.banking.core.model.dto.Account;
import com.banking.core.model.dto.response.AccountResponse;

public interface AccountService {

	AccountResponse createAccount(Optional<Account> account);

	AccountResponse getAccount(String accountNumber);

	AccountResponse updateAccount(Optional<Account> account);

	Optional<Account> updateAccountBalance(Optional<Account> account);

}
