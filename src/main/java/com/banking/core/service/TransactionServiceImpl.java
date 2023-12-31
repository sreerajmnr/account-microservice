package com.banking.core.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banking.core.constant.ErrorType;
import com.banking.core.constant.TransactionType;
import com.banking.core.exception.InsufficientFundException;
import com.banking.core.exception.ResourceNotFoundException;
import com.banking.core.exception.TransactionException;
import com.banking.core.model.dto.Account;
import com.banking.core.model.dto.request.TransactionRequest;
import com.banking.core.model.dto.response.AccountResponse;
import com.banking.core.model.dto.response.TransactionResponse;
import com.banking.core.model.entity.TransactionEntity;
import com.banking.core.model.mapper.TransactionMapper;
import com.banking.core.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	private static final BigDecimal MINMUM_REQUIRED_BALANCE = new BigDecimal("500.00");

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionMapper transactionMapper;

	@Override
	public TransactionResponse getTransactions(final String accountNumber, final int limit, final int start) {
		List<Optional<TransactionEntity>> transactions = transactionRepository
				.findLastNTransactionByAccountNumber(accountNumber, PageRequest.of(start, limit));
		if (transactions.isEmpty()) {
			throw new ResourceNotFoundException(ErrorType.RESOURCE_NOT_FOUND);
		}
		return new TransactionResponse(transactionMapper.convertToDto(transactions));
	}

	@Override
	@Transactional
	public void performTransaction(final Optional<TransactionRequest> transactionRequest) {
		if (transactionRequest.isPresent()) {
			AccountResponse accountResponse = accountService.getAccount(
					transactionRequest.get().transactionAccount());
			Optional<Account> account = accountResponse.bankAccount();

			if (validate(transactionRequest, account)) {

				updateAccountBalance(transactionRequest, account);
				createTransaction(transactionRequest);
			}
		}
	}

	private void createTransaction(final Optional<TransactionRequest> transactionRequest) {
		TransactionEntity transactionEntity = new TransactionEntity();
		transactionEntity.setTransactionAccount(transactionRequest.get().transactionAccount());
		transactionEntity.setTransactionAmount(transactionRequest.get().transactionAmount());
		transactionEntity.setTransactionType(transactionRequest.get().transactionType());
		transactionEntity.setTransactionDate(new Date());

		transactionRepository.save(transactionEntity);
	}

	private void updateAccountBalance(final Optional<TransactionRequest> transactionRequest,
			final Optional<Account> account) {
		BigDecimal accountBalance = account.get().accountBalance();
		BigDecimal transactionAmount = transactionRequest.get().transactionAmount();
		BigDecimal balanceAmount = null;

		if (TransactionType.WITHDRAW.equals(transactionRequest.get().transactionType())) {
			balanceAmount = accountBalance.subtract(transactionAmount);
		} else if (TransactionType.DEPOSIT.equals(transactionRequest.get().transactionType())) {
			balanceAmount = accountBalance.add(transactionAmount);
		} else {
			throw new TransactionException(ErrorType.INVALID_TRANSACTION);
		}

		Account transactionAccount = new Account(transactionRequest.get().transactionAccount(),
				null, null, null,
				balanceAmount, null);
		accountService.updateAccountBalance(Optional.of(transactionAccount));
	}

	private boolean validate(final Optional<TransactionRequest> transactionRequest,
			final Optional<Account> account) {
		if (account.isPresent()) {
			switch (transactionRequest.get().transactionType()) {
			case WITHDRAW:
				BigDecimal accountBalance = account.get().accountBalance();
				BigDecimal transactionAmount = transactionRequest.get().transactionAmount();
				BigDecimal balanceAmount = accountBalance.subtract(transactionAmount);
				if (accountBalance.compareTo(transactionAmount) == 1
						&& balanceAmount.compareTo(MINMUM_REQUIRED_BALANCE) == 1) {
					return true;
				} else {
					throw new InsufficientFundException(ErrorType.INSUFFICIENT_FUND);
				}
			case DEPOSIT:
				return true;
			default:
				throw new TransactionException(ErrorType.INVALID_TRANSACTION);
			}
		} else {
			throw new ResourceNotFoundException(ErrorType.RESOURCE_NOT_FOUND);
		}
	}

}
