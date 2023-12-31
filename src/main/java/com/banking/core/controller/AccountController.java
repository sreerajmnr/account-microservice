package com.banking.core.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.core.model.dto.Account;
import com.banking.core.model.dto.response.AccountResponse;
import com.banking.core.service.AccountService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1")
public final class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@PostMapping("/customers/urn:id:headers.x-customer-id/accounts")
	public ResponseEntity<?> createAccount(
			@RequestHeader("X-Customer-Id") @NotNull @NotBlank final String customerId,
			@Valid @RequestBody final Optional<Account> account) {

		accountService.createAccount(account);
		LOGGER.info("Account created for the customer {}", customerId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/customers/urn:id:headers.x-customer-id/accounts")
	public ResponseEntity<?> updateAccount(
			@RequestHeader("X-Customer-Id") @NotNull @NotBlank final String customerId,
			@Valid @RequestBody final Optional<Account> account) {

		accountService.updateAccount(account);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/customers/urn:id:headers.x-customer-id/accounts/urn:id:headers.x-account-number")
	public ResponseEntity<AccountResponse> getAccount(
			@RequestHeader("X-Customer-Id") @NotNull @NotBlank final String customerId,
			@RequestHeader("X-Account-Number") @NotNull @NotBlank final String accountNumber) {

		return ResponseEntity.ok(accountService.getAccount(accountNumber));
	}

}
