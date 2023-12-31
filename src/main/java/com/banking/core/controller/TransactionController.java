package com.banking.core.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.core.model.dto.request.TransactionRequest;
import com.banking.core.model.dto.response.TransactionResponse;
import com.banking.core.service.TransactionService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("/api/v1")
public final class TransactionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/accounts/urn:id:headers.x-account-number/transactions")
	public ResponseEntity<TransactionResponse> getTransactions(
			@RequestHeader("X-Account-Number") @NotNull @NotBlank final String accountNumber,
			@RequestParam @Positive final int pageLimit,
			@RequestParam @PositiveOrZero final int pageStart) {
		LOGGER.info("getTransactions");
		return ResponseEntity.ok(transactionService.getTransactions(accountNumber, pageLimit, pageStart));
	}

	@PostMapping("/accounts/urn:id:headers.x-account-number/transactions")
	public ResponseEntity<?> executeTransactions(
			@RequestHeader("X-Account-Number") @NotNull @NotBlank final String accountNumber,
			@Valid @RequestBody final Optional<TransactionRequest> transactionRequest) {
		LOGGER.info("executeTransactions");
		transactionService.performTransaction(transactionRequest);
		return ResponseEntity.noContent().build();
	}

}
