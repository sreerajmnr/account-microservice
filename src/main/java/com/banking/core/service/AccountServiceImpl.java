package com.banking.core.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.core.constant.ErrorType;
import com.banking.core.exception.ResourceNotFoundException;
import com.banking.core.model.dto.Account;
import com.banking.core.model.dto.response.AccountResponse;
import com.banking.core.model.entity.AccountEntity;
import com.banking.core.model.mapper.AccountMapper;
import com.banking.core.repository.AccountRepository;

@Service
public final class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public AccountResponse createAccount(final Optional<Account> account) {
		Optional<AccountEntity> accountEntity = accountMapper.convertToEntity(account);
		if (accountEntity.isPresent()) {
			return new AccountResponse(
					accountMapper.convertToDto(
							Optional.of(accountRepository.save(accountEntity.get()))));
		} else {
			throw new ResourceNotFoundException(ErrorType.RESOURCE_NOT_FOUND);
		}
	}

	@Override
	public AccountResponse getAccount(final String accountNumber) {
		Optional<AccountEntity> accountEntity = accountRepository.findByAccountNumber(accountNumber);
		if (accountEntity.isPresent()) {
			return new AccountResponse(accountMapper.convertToDto(accountEntity));
		} else {
			throw new ResourceNotFoundException(ErrorType.RESOURCE_NOT_FOUND);
		}
	}

	@Override
	public AccountResponse updateAccount(final Optional<Account> account) {
		Optional<AccountEntity> accountEntity = accountRepository.findByAccountNumber(
				account.get().accountNumber());
		if (accountEntity.isPresent()) {
			accountEntity.get().setBranchCode(account.get().branchCode());
			accountEntity.get().setAccountStatus(account.get().accountStatus());
			accountEntity.get().setAccountBalance(account.get().accountBalance());
			return new AccountResponse(
					accountMapper.convertToDto(
							Optional.of(accountRepository.save(accountEntity.get()))));
		} else {
			throw new ResourceNotFoundException(ErrorType.RESOURCE_NOT_FOUND);
		}
	}

	@Override
	public Optional<Account> updateAccountBalance(final Optional<Account> account) {
		Optional<AccountEntity> accountEntity = accountRepository.findByAccountNumber(
				account.get().accountNumber());
		if (accountEntity.isPresent()) {
			accountEntity.get().setAccountBalance(account.get().accountBalance());
			return accountMapper.convertToDto(
					Optional.of(accountRepository.save(accountEntity.get())));
		} else {
			throw new ResourceNotFoundException(ErrorType.RESOURCE_NOT_FOUND);
		}
	}

}
