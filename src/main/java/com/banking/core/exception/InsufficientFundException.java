package com.banking.core.exception;

import com.banking.core.constant.ErrorType;

public class InsufficientFundException extends BaseException {

	private static final long serialVersionUID = 1L;

	public InsufficientFundException(final ErrorType errorType) {
		super(errorType);
	}

}
