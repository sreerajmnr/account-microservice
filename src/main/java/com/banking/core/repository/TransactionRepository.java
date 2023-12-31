package com.banking.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banking.core.model.entity.TransactionEntity;

@Repository
public interface TransactionRepository
		extends JpaRepository<TransactionEntity, Long>, PagingAndSortingRepository<TransactionEntity, Long> {

	@Query("SELECT txn FROM TransactionEntity txn WHERE txn.transactionAccount = :accountNumber")
	List<Optional<TransactionEntity>> findLastNTransactionByAccountNumber(
			@Param("accountNumber") String accountNumber, Pageable paging);
}
