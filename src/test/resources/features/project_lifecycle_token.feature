@regression @project @token
Feature: Project Lifecycle using Token Authentication
  As a Todo.ly API client
  I want to register a user, request an authentication token, and manage projects (Create, Update, Delete)
  So that I can verify token-based authorization and project lifecycle operations

  @smoke @crud
  Scenario: Create a user, generate an auth token, and perform Create, Update and Delete operations on a Project
    Given a new user payload with unique email, name "QA Automation User" and password "Pass12345!"
    When the user registration request is sent
    Then the user is created successfully with status code 200

    When an authentication token is requested using the user credentials
    Then the token should be successfully generated

    When a new project is created with name "Project Alpha" using the authentication token
    Then the project should be created successfully with status code 200

    When the project name is updated to "Project Alpha Updated" using the authentication token
    Then the project name should be "Project Alpha Updated"

    When the project is deleted using the authentication token
    Then the project should be marked as deleted
