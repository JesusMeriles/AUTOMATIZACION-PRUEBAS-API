package com.todoly.stepdefinitions;

import com.todoly.client.UserClient;
import com.todoly.context.TestContext;
import com.todoly.model.UserRequest;
import com.todoly.model.UserResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.util.UUID;

public class UserSteps {

    private final TestContext context;
    private final UserClient userClient;

    public UserSteps(TestContext context, UserClient userClient) {
        this.context = context;
        this.userClient = userClient;
    }

    @Given("a new user payload with unique email, name {string} and password {string}")
    public void aNewUserPayloadWithUniqueEmailNameAndPassword(String name, String password) {
        String uniqueEmail = "user_" + UUID.randomUUID().toString().substring(0, 8) + "@testingapi.com";
        UserRequest request = new UserRequest(uniqueEmail, name, password);
        context.setUserRequest(request);
    }

    @Given("a registered user exists with name {string} and password {string}")
    public void aRegisteredUserExistsWithNameAndPassword(String name, String password) {
        aNewUserPayloadWithUniqueEmailNameAndPassword(name, password);
        theUserRegistrationRequestIsSent();
        theUserIsCreatedSuccessfullyWithStatusCode(200);
    }

    @Given("a user payload with existing user's email, name {string} and password {string}")
    public void aUserPayloadWithExistingUsersEmailNameAndPassword(String name, String password) {
        String existingEmail = context.getUserRequest().getEmail();
        UserRequest duplicateRequest = new UserRequest(existingEmail, name, password);
        context.setUserRequest(duplicateRequest);
    }

    @When("the user registration request is sent")
    public void theUserRegistrationRequestIsSent() {
        Response response = userClient.createUser(context.getUserRequest());
        context.setLatestResponse(response);
        if (response.getStatusCode() == 200 && response.jsonPath().get("Id") != null) {
            context.setUserResponse(response.as(UserResponse.class));
        }
    }

    @Then("the user is created successfully with status code {int}")
    public void theUserIsCreatedSuccessfullyWithStatusCode(int expectedStatusCode) {
        Response response = context.getLatestResponse();
        Assertions.assertThat(response.getStatusCode())
                .as("User creation status code")
                .isEqualTo(expectedStatusCode);

        UserResponse userResponse = context.getUserResponse();
        Assertions.assertThat(userResponse).isNotNull();
        Assertions.assertThat(userResponse.getId()).isNotNull();
        Assertions.assertThat(userResponse.getEmail()).isEqualTo(context.getUserRequest().getEmail());
        Assertions.assertThat(userResponse.getFullName()).isEqualTo(context.getUserRequest().getFullName());
    }

    @When("the user updates their full name to {string}")
    public void theUserUpdatesTheirFullNameTo(String newFullName) {
        UserRequest updateRequest = new UserRequest();
        updateRequest.setFullName(newFullName);

        Response response;
        if (context.getToken() != null) {
            response = userClient.updateUserWithToken(updateRequest, context.getToken());
        } else {
            response = userClient.updateUserWithBasicAuth(
                    updateRequest,
                    context.getUserRequest().getEmail(),
                    context.getUserRequest().getPassword()
            );
        }

        context.setLatestResponse(response);
        if (response.getStatusCode() == 200 && response.jsonPath().get("Id") != null) {
            context.setUserResponse(response.as(UserResponse.class));
        }
    }

    @When("the user updates their full name to {string} using invalid token {string}")
    public void theUserUpdatesTheirFullNameToUsingInvalidToken(String newFullName, String invalidToken) {
        UserRequest updateRequest = new UserRequest();
        updateRequest.setFullName(newFullName);
        Response response = userClient.updateUserWithToken(updateRequest, invalidToken);
        context.setLatestResponse(response);
    }

    @When("a request to update user is sent without authentication")
    public void aRequestToUpdateUserIsSentWithoutAuthentication() {
        UserRequest updateRequest = new UserRequest();
        updateRequest.setFullName("Unauthorized Update");
        Response response = userClient.updateUserWithoutAuth(updateRequest);
        context.setLatestResponse(response);
    }

    @Then("the user profile should have full name {string}")
    public void theUserProfileShouldHaveFullName(String expectedName) {
        UserResponse response = context.getUserResponse();
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getFullName())
                .as("Updated user full name")
                .isEqualTo(expectedName);
    }

    @When("the user profile is retrieved")
    public void theUserProfileIsRetrieved() {
        Response response;
        if (context.getToken() != null) {
            response = userClient.getUserWithToken(context.getToken());
        } else {
            response = userClient.getUserWithBasicAuth(
                    context.getUserRequest().getEmail(),
                    context.getUserRequest().getPassword()
            );
        }
        context.setLatestResponse(response);
        if (response.getStatusCode() == 200 && response.jsonPath().get("Id") != null) {
            context.setUserResponse(response.as(UserResponse.class));
        }
    }

    @When("a request to get user profile is sent without authentication")
    public void aRequestToGetUserProfileIsSentWithoutAuthentication() {
        Response response = userClient.getUserWithoutAuth();
        context.setLatestResponse(response);
    }

    @When("the current user account is deleted")
    public void theCurrentUserAccountIsDeleted() {
        Response response;
        if (context.getToken() != null) {
            response = userClient.deleteUserWithToken(context.getToken());
        } else {
            response = userClient.deleteUserWithBasicAuth(
                    context.getUserRequest().getEmail(),
                    context.getUserRequest().getPassword()
            );
        }

        context.setLatestResponse(response);
        // Clear user response in context since user was deleted
        context.setUserResponse(null);
    }

    @When("a request to delete user is sent without authentication")
    public void aRequestToDeleteUserIsSentWithoutAuthentication() {
        Response response = userClient.deleteUserWithoutAuth();
        context.setLatestResponse(response);
    }

    @When("a request to delete user is sent using invalid token {string}")
    public void aRequestToDeleteUserIsSentUsingInvalidToken(String invalidToken) {
        Response response = userClient.deleteUserWithToken(invalidToken);
        context.setLatestResponse(response);
    }

    @Then("the user account should no longer exist")
    public void theUserAccountShouldNoLongerExist() {
        Response response = userClient.getUserWithBasicAuth(
                context.getUserRequest().getEmail(),
                context.getUserRequest().getPassword()
        );
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(response.jsonPath().getString("ErrorMessage"))
                .contains("Account doesn't exist");
    }
}
