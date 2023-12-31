package com.banking.core.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.banking.core.constant.TransactionType;

public record Transaction(Long transactionId, TransactionType transactionType, Date transactionDate,
		String transactionAccount, BigDecimal transactionAmount) {
}
