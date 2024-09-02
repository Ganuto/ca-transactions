package com.project.transactions;

import com.project.transactions.util.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;

public class IntegrationRequests {

    public static Response post(String path, Object body) throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        return RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(TestUtils.convertObjectToJsonString(body))
                .post(path);
    }

    public static Response get(String path) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        return RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .get(path);
    }
}
