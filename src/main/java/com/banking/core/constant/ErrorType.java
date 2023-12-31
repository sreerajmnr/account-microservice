package com.banking.core.constant;

public enum ErrorType {

	REQUEST_VALIDATION_FAILED("ACC-SER-ERR-0001", "exception.requestValidationError"),
	RESOURCE_NOT_FOUND("ACC-SER-ERR-0002", "exception.resourceNotFoundError"),
	INVALID_TRANSACTION("ACC-SER-ERR-0003", "exception.invalidTransactionError"),
	INSUFFICIENT_FUND("ACC-SER-ERR-0004", "exception.insufficientFundError");

	private final String errorCode;
	private final String messageKey;

	ErrorType(final String code, final String message) {
		this.errorCode = code;
		this.messageKey = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getMessageKey() {
		return messageKey;
	}

}
