package com.todoly.stepdefinitions;

import com.todoly.client.AuthClient;
import com.todoly.context.TestContext;
import com.todoly.model.TokenResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class AuthSteps {

    private final TestContext context;
    private final AuthClient authClient;

    public AuthSteps(TestContext context, AuthClient authClient) {
        this.context = context;
        this.authClient = authClient;
    }

    @When("an authentication token is requested using the user credentials")
    public void anAuthenticationTokenIsRequestedUsingTheUserCredentials() {
        Response response = authClient.getAuthenticationToken(
                context.getUserRequest().getEmail(),
                context.getUserRequest().getPassword()
        );
        context.setLatestResponse(response);
        if (response.getStatusCode() == 200 && response.jsonPath().get("TokenString") != null) {
            TokenResponse tokenResp = response.as(TokenResponse.class);
            context.setTokenResponse(tokenResp);
            context.setToken(tokenResp.getTokenString());
        }
    }

    @When("an authentication token is requested with email {string} and password {string}")
    public void anAuthenticationTokenIsRequestedWithEmailAndPassword(String email, String password) {
        Response response = authClient.getAuthenticationToken(email, password);
        context.setLatestResponse(response);
        if (response.getStatusCode() == 200 && response.jsonPath().get("TokenString") != null) {
            TokenResponse tokenResp = response.as(TokenResponse.class);
            context.setTokenResponse(tokenResp);
            context.setToken(tokenResp.getTokenString());
        }
    }

    @When("an authentication token is requested with incorrect password {string}")
    public void anAuthenticationTokenIsRequestedWithIncorrectPassword(String wrongPassword) {
        Response response = authClient.getAuthenticationToken(
                context.getUserRequest().getEmail(),
                wrongPassword
        );
        context.setLatestResponse(response);
    }

    @When("an authentication token is requested without authentication credentials")
    public void anAuthenticationTokenIsRequestedWithoutAuthenticationCredentials() {
        Response response = authClient.getAuthenticationTokenWithoutAuth();
        context.setLatestResponse(response);
    }

    @Then("the token should be successfully generated")
    public void theTokenShouldBeSuccessfullyGenerated() {
        Response response = context.getLatestResponse();
        Assertions.assertThat(response.getStatusCode())
                .as("Token response status code")
                .isEqualTo(200);

        TokenResponse tokenResponse = context.getTokenResponse();
        Assertions.assertThat(tokenResponse).isNotNull();
        Assertions.assertThat(tokenResponse.getTokenString())
                .as("Generated TokenString")
                .isNotNull()
                .isNotEmpty();
        Assertions.assertThat(tokenResponse.getUserEmail())
                .isEqualTo(context.getUserRequest().getEmail());
    }
}
