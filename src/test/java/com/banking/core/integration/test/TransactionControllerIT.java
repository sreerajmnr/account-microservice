package com.banking.core.integration.test;

import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class TransactionControllerIT extends BaseIntegrationTest {

	@Test
	public void shouldGetAllTransactions() throws Exception {

		RestAssured.given().contentType(ContentType.JSON).when().header("X-Account-Number", "123").get(
				"/account-service/api/v1/accounts/urn:id:headers.x-account-number/transactions?pageLimit=10&pageStart=0")
				.then().statusCode(200);
	}

}
