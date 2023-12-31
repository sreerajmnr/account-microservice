package com.banking.core.model.dto.response;

import java.util.List;
import java.util.Optional;

import com.banking.core.model.dto.Transaction;

public record TransactionResponse(List<Optional<Transaction>> accountTransactions) {
}
