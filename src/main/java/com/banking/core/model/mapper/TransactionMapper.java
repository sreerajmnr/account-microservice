package com.banking.core.model.mapper;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banking.core.model.dto.Transaction;
import com.banking.core.model.entity.TransactionEntity;

@Service
public final class TransactionMapper extends BaseMapper<TransactionEntity, Transaction> {

	@Override
	public Optional<TransactionEntity> convertToEntity(final Optional<Transaction> dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Transaction> convertToDto(final Optional<TransactionEntity> entity) {
		if (entity.isPresent()) {
			TransactionEntity transactionEntity = entity.get();
			return Optional.of(new Transaction(transactionEntity.getTransactionId(),
					transactionEntity.getTransactionType(), transactionEntity.getTransactionDate(),
					transactionEntity.getTransactionAccount(),
					transactionEntity.getTransactionAmount()));
		}
		return Optional.empty();
	}

}
