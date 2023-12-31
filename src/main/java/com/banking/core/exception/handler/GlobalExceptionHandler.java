package com.banking.core.exception.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.banking.core.constant.ErrorType;
import com.banking.core.exception.InsufficientFundException;
import com.banking.core.exception.ResourceNotFoundException;
import com.banking.core.exception.TransactionException;
import com.banking.core.model.dto.ErrorResponse;

@ControllerAdvice
public final class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String FIELD_ERROR = "Field Error";
	private static final String FIELD_NAME = "Field Name";

	private final ResourceBundleMessageSource messageSource;

	GlobalExceptionHandler(final ResourceBundleMessageSource source) {
		this.messageSource = source;
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<ErrorResponse> handleNotFoundException(final ResourceNotFoundException ex,
			final WebRequest request) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildErrorResponse(
				ex.getErrorType().getErrorCode(), ex.getErrorType().getMessageKey(),
				new ArrayList<>()));
	}

	@ExceptionHandler(TransactionException.class)
	protected ResponseEntity<ErrorResponse> handleTransactionException(final TransactionException ex,
			final WebRequest request) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildErrorResponse(
				ex.getErrorType().getErrorCode(), ex.getErrorType().getMessageKey(),
				new ArrayList<>()));
	}

	@ExceptionHandler(InsufficientFundException.class)
	protected ResponseEntity<ErrorResponse> handleInsufficientFundException(final InsufficientFundException ex,
			final WebRequest request) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(
				ex.getErrorType().getErrorCode(), ex.getErrorType().getMessageKey(),
				new ArrayList<>()));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<ErrorResponse> handleNotFoundException(final MethodArgumentTypeMismatchException ex,
			final WebRequest request) {

		Map<String, String> errors = new HashMap<>();
		errors.put(FIELD_NAME, ex.getName());
		errors.put(FIELD_ERROR, ex.getMessage());
		List<Map<String, String>> errorDetails = new ArrayList<>();
		errorDetails.add(errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(buildErrorResponse(ErrorType.REQUEST_VALIDATION_FAILED.getErrorCode(),
						ErrorType.REQUEST_VALIDATION_FAILED.getMessageKey(), errorDetails));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {

		List<Map<String, String>> errorDetails = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			Map<String, String> errors = new HashMap<>();
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(FIELD_NAME, fieldName);
			errors.put(FIELD_ERROR, errorMessage);
			errorDetails.add(errors);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(buildErrorResponse(ErrorType.REQUEST_VALIDATION_FAILED.getErrorCode(),
						ErrorType.REQUEST_VALIDATION_FAILED.getMessageKey(), errorDetails));
	}

	@Override
	protected ResponseEntity<Object> handleHandlerMethodValidationException(
			final HandlerMethodValidationException ex, final HttpHeaders headers,
			final HttpStatusCode status, final WebRequest request) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(buildErrorResponse(ErrorType.REQUEST_VALIDATION_FAILED.getErrorCode(),
						ErrorType.REQUEST_VALIDATION_FAILED.getMessageKey(),
						new ArrayList<>()));
	}

	private ErrorResponse buildErrorResponse(final String errorCode, final String messageKey,
			final List<Map<String, String>> errorDetails) {
		return new ErrorResponse(errorCode, messageSource.getMessage(messageKey, null,
				LocaleContextHolder.getLocale()), LocalDateTime.now(), errorDetails);
	}

}
