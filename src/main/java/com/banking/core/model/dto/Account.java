package com.banking.core.model.dto;

import java.math.BigDecimal;

import com.banking.core.constant.AccountStatus;
import com.banking.core.constant.AccountType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record Account(
		@NotBlank @Size(min = MIN, message = "{exception.accountNumberValidationError}")
		@Size(max = MAX, message = "{exception.accountNumberValidationError}") String accountNumber,
		@NotBlank String branchCode, @NotNull AccountType accountType, @NotNull AccountStatus accountStatus,
		@Positive BigDecimal accountBalance, @Positive Long customerId) {

	private static final int MAX = 10;
	private static final int MIN = 5;

}
