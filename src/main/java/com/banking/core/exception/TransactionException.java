package com.banking.core.exception;

import com.banking.core.constant.ErrorType;

public class TransactionException extends BaseException {

	private static final long serialVersionUID = 1L;

	public TransactionException(final ErrorType errorType) {
		super(errorType);
	}

}
