package com.todoly.client;

import com.todoly.model.UserRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserClient extends BaseClient {

    public Response createUser(UserRequest userRequest) {
        return RestAssured.given()
                .spec(getRequestSpec())
                .body(userRequest)
                .when()
                .post(config.getUserEndpoint())
                .then()
                .extract()
                .response();
    }

    public Response getUserWithToken(String token) {
        return RestAssured.given()
                .spec(getRequestSpecWithToken(token))
                .when()
                .get(config.getUserEndpoint())
                .then()
                .extract()
                .response();
    }

    public Response getUserWithBasicAuth(String email, String password) {
        return RestAssured.given()
                .spec(getRequestSpecWithBasicAuth(email, password))
                .when()
                .get(config.getUserEndpoint())
                .then()
                .extract()
                .response();
    }

    public Response getUserWithoutAuth() {
        return RestAssured.given()
                .spec(getRequestSpec())
                .when()
                .get(config.getUserEndpoint())
                .then()
                .extract()
                .response();
    }

    public Response updateUserWithToken(UserRequest userRequest, String token) {
        return RestAssured.given()
                .spec(getRequestSpecWithToken(token))
                .body(userRequest)
                .when()
                .put(config.getUserCurrentEndpoint())
                .then()
                .extract()
                .response();
    }

    public Response updateUserWithBasicAuth(UserRequest userRequest, String email, String password) {
        return RestAssured.given()
                .spec(getRequestSpecWithBasicAuth(email, password))
                .body(userRequest)
                .when()
                .put(config.getUserCurrentEndpoint())
                .then()
                .extract()
                .response();
    }

    public Response deleteUserWithToken(String token) {
        return RestAssured.given()
                .spec(getRequestSpecWithToken(token))
                .when()
                .delete(config.getUserCurrentEndpoint())
                .then()
                .extract()
                .response();
    }

    public Response deleteUserWithBasicAuth(String email, String password) {
        return RestAssured.given()
                .spec(getRequestSpecWithBasicAuth(email, password))
                .when()
                .delete(config.getUserCurrentEndpoint())
                .then()
                .extract()
                .response();
    }

    public Response deleteUserWithoutAuth() {
        return RestAssured.given()
                .spec(getRequestSpec())
                .when()
                .delete(config.getUserCurrentEndpoint())
                .then()
                .extract()
                .response();
    }

    public Response updateUserWithoutAuth(UserRequest userRequest) {
        return RestAssured.given()
                .spec(getRequestSpec())
                .body(userRequest)
                .when()
                .put(config.getUserCurrentEndpoint())
                .then()
                .extract()
                .response();
    }
}
