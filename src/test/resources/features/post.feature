@post @create @crud
Feature: Create Operations (POST)
  As a Todo.ly API consumer
  I want to create users and projects using POST requests
  So that I can verify resource creation and validation rules

  @smoke @positive
  Scenario: Successfully create a new user
    Given a new user payload with unique email, name "Jane Doe" and password "Pass12345!"
    When the user registration request is sent
    Then the user is created successfully with status code 200

  @smoke @positive
  Scenario: Successfully create a new project using an authentication token
    Given a registered user exists with name "Project Owner" and password "Pass12345!"
    And an authentication token is requested using the user credentials
    When a new project is created with name "Design Project" using the authentication token
    Then the project should be created successfully with status code 200

  @negative
  Scenario: Fail to create user with already existing email address
    Given a registered user exists with name "Primary User" and password "Pass12345!"
    And a user payload with existing user's email, name "Duplicate User" and password "OtherPass123"
    When the user registration request is sent
    Then the response status code should be 200
    And the response should contain error message "Account with this email address already exists"
    And the response should contain error code 201

  @negative
  Scenario: Fail to create project without authentication token
    When a new project is created with name "Unauthorized Project" without an authentication token
    Then the response status code should be 200
    And the response should contain error message "Not Authenticated"
    And the response should contain error code 102

  @negative
  Scenario: Fail to create project with invalid token
    When a new project is created with name "Fake Token Project" using invalid token "invalid_token_999"
    Then the response status code should be 200
    And the response should contain error message "Not Authenticated"
    And the response should contain error code 102
