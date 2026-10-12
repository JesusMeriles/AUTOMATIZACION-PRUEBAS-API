package com.todoly.stepdefinitions;

import com.todoly.context.TestContext;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class CommonSteps {

    private final TestContext context;

    public CommonSteps(TestContext context) {
        this.context = context;
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        Response response = context.getLatestResponse();
        Assertions.assertThat(response)
                .as("Latest HTTP response")
                .isNotNull();
        Assertions.assertThat(response.getStatusCode())
                .as("Response status code")
                .isEqualTo(expectedStatusCode);
    }

    @Then("the response should contain error message {string}")
    public void theResponseShouldContainErrorMessage(String expectedErrorMessage) {
        Response response = context.getLatestResponse();
        Assertions.assertThat(response)
                .as("Latest HTTP response")
                .isNotNull();

        String actualMessage = response.jsonPath().getString("ErrorMessage");
        Assertions.assertThat(actualMessage)
                .as("API ErrorMessage")
                .isEqualTo(expectedErrorMessage);
    }

    @Then("the response should contain error code {int}")
    public void theResponseShouldContainErrorCode(int expectedErrorCode) {
        Response response = context.getLatestResponse();
        Assertions.assertThat(response)
                .as("Latest HTTP response")
                .isNotNull();

        Integer actualCode = response.jsonPath().getInt("ErrorCode");
        Assertions.assertThat(actualCode)
                .as("API ErrorCode")
                .isEqualTo(expectedErrorCode);
    }
}
