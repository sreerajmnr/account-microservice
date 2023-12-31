package com.banking.core.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ErrorResponse(String errorCode, String errorMessage, LocalDateTime timeStamp,
		List<Map<String, String>> details) {
}
