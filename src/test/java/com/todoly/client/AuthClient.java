package com.todoly.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AuthClient extends BaseClient {

    public Response getAuthenticationToken(String email, String password) {
        return RestAssured.given()
                .spec(getRequestSpecWithBasicAuth(email, password))
                .when()
                .get(config.getTokenEndpoint())
                .then()
                .extract()
                .response();
    }

    public Response getAuthenticationTokenWithoutAuth() {
        return RestAssured.given()
                .spec(getRequestSpec())
                .when()
                .get(config.getTokenEndpoint())
                .then()
                .extract()
                .response();
    }
}
