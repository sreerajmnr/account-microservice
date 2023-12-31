package com.banking.core.model.dto.response;

import java.util.Optional;

import com.banking.core.model.dto.Account;

public record AccountResponse(Optional<Account> bankAccount) {
}
