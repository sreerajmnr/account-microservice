package com.banking.core.exception;

import com.banking.core.constant.ErrorType;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorType errorType;

	public BaseException(final ErrorType type) {
		super();
		errorType = type;
	}

	public final ErrorType getErrorType() {
		return errorType;
	}

}
