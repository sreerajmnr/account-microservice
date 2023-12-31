package com.banking.core.model.mapper;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banking.core.model.dto.Account;
import com.banking.core.model.entity.AccountEntity;
import com.banking.core.model.entity.CustomerEntity;

@Service
public final class AccountMapper extends BaseMapper<AccountEntity, Account> {

	@Override
	public Optional<AccountEntity> convertToEntity(final Optional<Account> dto) {
		if (dto.isPresent()) {
			Account account = dto.get();
			AccountEntity entity = new AccountEntity();
			entity.setAccountNumber(account.accountNumber());
			entity.setBranchCode(account.branchCode());
			entity.setAccountType(account.accountType());
			entity.setAccountStatus(account.accountStatus());
			entity.setAccountBalance(account.accountBalance());
			CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setId(account.customerId());
			entity.setCustomer(customerEntity);
			return Optional.of(entity);
		}
		return Optional.empty();
	}

	@Override
	public Optional<Account> convertToDto(final Optional<AccountEntity> entity) {
		if (entity.isPresent()) {
			AccountEntity accountEntity = entity.get();
			return Optional.of(new Account(accountEntity.getAccountNumber(), accountEntity.getBranchCode(),
					accountEntity.getAccountType(), accountEntity.getAccountStatus(),
					accountEntity.getAccountBalance(), accountEntity.getCustomer().getId()));
		}
		return Optional.empty();
	}

}
