package com.banking.core.service;

import java.util.Optional;

import com.banking.core.model.dto.request.TransactionRequest;
import com.banking.core.model.dto.response.TransactionResponse;

public interface TransactionService {

	TransactionResponse getTransactions(String accountNumber, int pageLimit, int pageStart);

	void performTransaction(Optional<TransactionRequest> transactionRequest);
}
