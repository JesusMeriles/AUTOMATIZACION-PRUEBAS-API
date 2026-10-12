package com.todoly.client;

import com.todoly.config.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Base64;

public abstract class BaseClient {

    protected final ApiConfig config;

    public BaseClient() {
        this.config = ApiConfig.getInstance();
    }

    protected RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(config.getBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .build();
    }

    protected RequestSpecification getRequestSpecWithToken(String token) {
        return getRequestSpec()
                .header("Token", token);
    }

    protected RequestSpecification getRequestSpecWithBasicAuth(String email, String password) {
        String authString = email + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(authString.getBytes());
        return getRequestSpec()
                .header("Authorization", "Basic " + encodedAuth);
    }
}
