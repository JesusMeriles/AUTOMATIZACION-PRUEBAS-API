package com.todoly.client;

import com.todoly.model.ProjectRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProjectClient extends BaseClient {

    public Response createProject(ProjectRequest projectRequest, String token) {
        return RestAssured.given()
                .spec(getRequestSpecWithToken(token))
                .body(projectRequest)
                .when()
                .post(config.getProjectsEndpoint())
                .then()
                .extract()
                .response();
    }

    public Response getProject(long id, String token) {
        return RestAssured.given()
                .spec(getRequestSpecWithToken(token))
                .when()
                .get(config.getProjectItemEndpoint(id))
                .then()
                .extract()
                .response();
    }

    public Response updateProject(long id, ProjectRequest projectRequest, String token) {
        return RestAssured.given()
                .spec(getRequestSpecWithToken(token))
                .body(projectRequest)
                .when()
                .put(config.getProjectItemEndpoint(id))
                .then()
                .extract()
                .response();
    }

    public Response deleteProject(long id, String token) {
        return RestAssured.given()
                .spec(getRequestSpecWithToken(token))
                .when()
                .delete(config.getProjectItemEndpoint(id))
                .then()
                .extract()
                .response();
    }

    public Response createProjectWithoutToken(ProjectRequest projectRequest) {
        return RestAssured.given()
                .spec(getRequestSpec())
                .body(projectRequest)
                .when()
                .post(config.getProjectsEndpoint())
                .then()
                .extract()
                .response();
    }

    public Response getProjectWithoutToken(long id) {
        return RestAssured.given()
                .spec(getRequestSpec())
                .when()
                .get(config.getProjectItemEndpoint(id))
                .then()
                .extract()
                .response();
    }

    public Response updateProjectWithoutToken(long id, ProjectRequest projectRequest) {
        return RestAssured.given()
                .spec(getRequestSpec())
                .body(projectRequest)
                .when()
                .put(config.getProjectItemEndpoint(id))
                .then()
                .extract()
                .response();
    }

    public Response deleteProjectWithoutToken(long id) {
        return RestAssured.given()
                .spec(getRequestSpec())
                .when()
                .delete(config.getProjectItemEndpoint(id))
                .then()
                .extract()
                .response();
    }
}
