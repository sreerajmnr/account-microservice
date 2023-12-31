package com.banking.core.exception;

import com.banking.core.constant.ErrorType;

public class ResourceNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(final ErrorType errorType) {
		super(errorType);
	}

}
