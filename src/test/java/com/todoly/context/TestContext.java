package com.todoly.context;

import com.todoly.model.ProjectRequest;
import com.todoly.model.ProjectResponse;
import com.todoly.model.TokenResponse;
import com.todoly.model.UserRequest;
import com.todoly.model.UserResponse;
import io.restassured.response.Response;

public class TestContext {

    private UserRequest userRequest;
    private UserResponse userResponse;
    private TokenResponse tokenResponse;
    private String token;
    private ProjectRequest projectRequest;
    private ProjectResponse projectResponse;
    private Response latestResponse;

    public void reset() {
        this.userRequest = null;
        this.userResponse = null;
        this.tokenResponse = null;
        this.token = null;
        this.projectRequest = null;
        this.projectResponse = null;
        this.latestResponse = null;
    }

    public UserRequest getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public TokenResponse getTokenResponse() {
        return tokenResponse;
    }

    public void setTokenResponse(TokenResponse tokenResponse) {
        this.tokenResponse = tokenResponse;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ProjectRequest getProjectRequest() {
        return projectRequest;
    }

    public void setProjectRequest(ProjectRequest projectRequest) {
        this.projectRequest = projectRequest;
    }

    public ProjectResponse getProjectResponse() {
        return projectResponse;
    }

    public void setProjectResponse(ProjectResponse projectResponse) {
        this.projectResponse = projectResponse;
    }

    public Response getLatestResponse() {
        return latestResponse;
    }

    public void setLatestResponse(Response latestResponse) {
        this.latestResponse = latestResponse;
    }
}
