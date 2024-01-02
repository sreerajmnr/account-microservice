package com.banking.core.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.banking.core.model.dto.ErrorResponse;
import com.banking.core.model.dto.response.TransactionResponse;
import com.banking.core.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

	@MockBean
	private TransactionService transactionService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Case 1 - Test the get transactions controller method valid scenario")
	void testGetTransactions() throws Exception {

		// prepare input data
		final String inputHeaderAccountNumber = "1234";
		final String inputParameterPageLimit = "10";
		final String inputParameterPageStart = "0";
		// prepare expected data
		final TransactionResponse expectedResponse = getExpectedTransactionResponse();
		// prepare mock data
		final TransactionResponse mockResponse = getMockTransactionResponse();

		// configure mock dependencies
		Mockito.when(transactionService.getTransactions(anyString(), anyInt(), anyInt())).thenReturn(mockResponse);

		// execute the test
		final MvcResult mvcResult = mockMvc
				.perform(get("/api/v1/accounts/urn:id:headers.x-account-number/transactions")
						.header("X-Account-Number", inputHeaderAccountNumber)
						.param("pageLimit", inputParameterPageLimit).param("pageStart", inputParameterPageStart))
				.andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		final TransactionResponse actualResponse = objectMapper
				.readValue(mvcResult.getResponse().getContentAsByteArray(), TransactionResponse.class);

		// verify the result
		assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedResponse);
	}

	@Test
	@DisplayName("Case 2 - Test the get transactions controller method invalid request")
	void testGetTransactionsNoResult() throws Exception {

		// prepare input data
		final String inputHeaderAccountNumber = ""; // invalid account number
		final String inputParameterPageLimit = "10";
		final String inputParameterPageStart = "0";
		// prepare expected data
		final ErrorResponse expectedResponse = getExpectedErrorResponse();

		// configure mock dependencies
		Mockito.when(transactionService.getTransactions(anyString(), anyInt(), anyInt())).thenReturn(null);

		// execute the test
		final MvcResult mvcResult = mockMvc
				.perform(get("/api/v1/accounts/urn:id:headers.x-account-number/transactions")
						.header("X-Account-Number", inputHeaderAccountNumber)
						.param("pageLimit", inputParameterPageLimit).param("pageStart", inputParameterPageStart))
				.andExpectAll(status().isBadRequest(), content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		final ErrorResponse actualResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(),
				ErrorResponse.class);

		// verify the result
		assertThat(actualResponse).usingRecursiveComparison().ignoringFields("timeStamp").isEqualTo(expectedResponse);
	}

	private TransactionResponse getMockTransactionResponse() throws Exception {
		TransactionResponse mockResponse = objectMapper.readValue(
				new File("src/test/resources/data/expected/transactions_case_1.json"), TransactionResponse.class);
		return mockResponse;
	}

	private TransactionResponse getExpectedTransactionResponse() throws Exception {
		TransactionResponse expectedResponse = objectMapper.readValue(
				new File("src/test/resources/data/expected/transactions_case_1.json"), TransactionResponse.class);
		return expectedResponse;
	}

	private ErrorResponse getExpectedErrorResponse() throws Exception {
		ErrorResponse expectedResponse = objectMapper
				.readValue(new File("src/test/resources/data/expected/transactions_case_2.json"), ErrorResponse.class);
		return expectedResponse;
	}

}
