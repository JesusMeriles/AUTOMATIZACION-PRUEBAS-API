package com.todoly.stepdefinitions;

import com.todoly.client.ProjectClient;
import com.todoly.context.TestContext;
import com.todoly.model.ProjectRequest;
import com.todoly.model.ProjectResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class ProjectSteps {

    private final TestContext context;
    private final ProjectClient projectClient;

    public ProjectSteps(TestContext context, ProjectClient projectClient) {
        this.context = context;
        this.projectClient = projectClient;
    }

    @When("a new project is created with name {string} using the authentication token")
    public void aNewProjectIsCreatedWithNameUsingTheAuthenticationToken(String projectName) {
        ProjectRequest request = new ProjectRequest(projectName);
        context.setProjectRequest(request);

        Response response = projectClient.createProject(request, context.getToken());
        context.setLatestResponse(response);

        if (response.getStatusCode() == 200 && response.jsonPath().get("Id") != null) {
            ProjectResponse projectResponse = response.as(ProjectResponse.class);
            context.setProjectResponse(projectResponse);
        }
    }

    @When("a new project is created with name {string} using invalid token {string}")
    public void aNewProjectIsCreatedWithNameUsingInvalidToken(String projectName, String invalidToken) {
        ProjectRequest request = new ProjectRequest(projectName);
        Response response = projectClient.createProject(request, invalidToken);
        context.setLatestResponse(response);
    }

    @When("a new project is created with name {string} without an authentication token")
    public void aNewProjectIsCreatedWithNameWithoutAnAuthenticationToken(String projectName) {
        ProjectRequest request = new ProjectRequest(projectName);
        Response response = projectClient.createProjectWithoutToken(request);
        context.setLatestResponse(response);
    }

    @When("the project is retrieved by ID using the authentication token")
    public void theProjectIsRetrievedByIDUsingTheAuthenticationToken() {
        long projectId = context.getProjectResponse().getId();
        Response response = projectClient.getProject(projectId, context.getToken());
        context.setLatestResponse(response);

        if (response.getStatusCode() == 200 && response.jsonPath().get("Id") != null) {
            ProjectResponse projectResponse = response.as(ProjectResponse.class);
            context.setProjectResponse(projectResponse);
        }
    }

    @When("a project with ID {long} is requested using the authentication token")
    public void aProjectWithIDIsRequestedUsingTheAuthenticationToken(long projectId) {
        Response response = projectClient.getProject(projectId, context.getToken());
        context.setLatestResponse(response);
    }

    @When("a project with ID {long} is requested without an authentication token")
    public void aProjectWithIDIsRequestedWithoutAnAuthenticationToken(long projectId) {
        Response response = projectClient.getProjectWithoutToken(projectId);
        context.setLatestResponse(response);
    }

    @Then("the retrieved project should match the created project")
    public void theRetrievedProjectShouldMatchTheCreatedProject() {
        ProjectResponse projectResponse = context.getProjectResponse();
        Assertions.assertThat(projectResponse).isNotNull();
        Assertions.assertThat(projectResponse.getContent())
                .isEqualTo(context.getProjectRequest().getContent());
    }

    @Then("the project should be created successfully with status code {int}")
    public void theProjectShouldBeCreatedSuccessfullyWithStatusCode(int expectedStatusCode) {
        Response response = context.getLatestResponse();
        Assertions.assertThat(response.getStatusCode())
                .as("Create project status code")
                .isEqualTo(expectedStatusCode);

        ProjectResponse projectResponse = context.getProjectResponse();
        Assertions.assertThat(projectResponse).isNotNull();
        Assertions.assertThat(projectResponse.getId()).isNotNull();
        Assertions.assertThat(projectResponse.getContent()).isEqualTo(context.getProjectRequest().getContent());
        Assertions.assertThat(projectResponse.getDeleted()).isFalse();
    }

    @When("the project name is updated to {string} using the authentication token")
    public void theProjectNameIsUpdatedToUsingTheAuthenticationToken(String updatedProjectName) {
        long projectId = context.getProjectResponse().getId();
        ProjectRequest updateRequest = new ProjectRequest(updatedProjectName);
        context.setProjectRequest(updateRequest);

        Response response = projectClient.updateProject(projectId, updateRequest, context.getToken());
        context.setLatestResponse(response);

        if (response.getStatusCode() == 200 && response.jsonPath().get("Id") != null) {
            ProjectResponse projectResponse = response.as(ProjectResponse.class);
            context.setProjectResponse(projectResponse);
        }
    }

    @When("a project with ID {long} is updated to name {string} using invalid token {string}")
    public void aProjectWithIDIsUpdatedToNameUsingInvalidToken(long projectId, String newName, String invalidToken) {
        ProjectRequest updateRequest = new ProjectRequest(newName);
        Response response = projectClient.updateProject(projectId, updateRequest, invalidToken);
        context.setLatestResponse(response);
    }

    @Then("the project name should be {string}")
    public void theProjectNameShouldBe(String expectedProjectName) {
        ProjectResponse projectResponse = context.getProjectResponse();
        Assertions.assertThat(projectResponse).isNotNull();
        Assertions.assertThat(projectResponse.getContent())
                .as("Updated project content")
                .isEqualTo(expectedProjectName);
    }

    @When("the project is deleted using the authentication token")
    public void theProjectIsDeletedUsingTheAuthenticationToken() {
        long projectId = context.getProjectResponse().getId();
        Response response = projectClient.deleteProject(projectId, context.getToken());
        context.setLatestResponse(response);

        if (response.getStatusCode() == 200 && response.jsonPath().get("Id") != null) {
            ProjectResponse projectResponse = response.as(ProjectResponse.class);
            context.setProjectResponse(projectResponse);
        }
    }

    @When("a project with ID {long} is deleted using the authentication token")
    public void aProjectWithIDIsDeletedUsingTheAuthenticationToken(long projectId) {
        Response response = projectClient.deleteProject(projectId, context.getToken());
        context.setLatestResponse(response);
    }

    @When("a project with ID {long} is deleted using invalid token {string}")
    public void aProjectWithIDIsDeletedUsingInvalidToken(long projectId, String invalidToken) {
        Response response = projectClient.deleteProject(projectId, invalidToken);
        context.setLatestResponse(response);
    }

    @Then("the project should be marked as deleted")
    public void theProjectShouldBeMarkedAsDeleted() {
        Response response = context.getLatestResponse();
        Assertions.assertThat(response.getStatusCode())
                .as("Delete project status code")
                .isEqualTo(200);

        ProjectResponse projectResponse = context.getProjectResponse();
        Assertions.assertThat(projectResponse).isNotNull();
        Assertions.assertThat(projectResponse.getDeleted())
                .as("Project deleted flag")
                .isTrue();
    }
}
