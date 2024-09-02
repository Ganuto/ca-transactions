package com.project.transactions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class IntegrationRequests {

    public static Response post(String path, Object body) {
        return RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(body)
                .post(path);
    }

    public static Response get(String path) {
        return RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .get(path);
    }
}
