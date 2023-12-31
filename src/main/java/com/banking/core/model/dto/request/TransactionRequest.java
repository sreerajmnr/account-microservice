package com.banking.core.model.dto.request;

import java.math.BigDecimal;

import com.banking.core.constant.TransactionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record TransactionRequest(
		@NotBlank @Size(min = MIN, message = "{exception.accountNumberValidationError}")
		@Size(max = MAX, message = "{exception.accountNumberValidationError}") String transactionAccount,
		@NotNull TransactionType transactionType, @NotNull @Positive BigDecimal transactionAmount) {

	private static final int MAX = 10;
	private static final int MIN = 5;

}
