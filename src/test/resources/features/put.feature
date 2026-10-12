@put @update @crud
Feature: Update Operations (PUT)
  As a Todo.ly API consumer
  I want to update users and projects using PUT requests
  So that I can verify resource updates and modification security

  @positive
  Scenario: Successfully update user profile name
    Given a registered user exists with name "Original Name" and password "Pass12345!"
    When the user updates their full name to "Updated Name"
    Then the user profile should have full name "Updated Name"
    And the response status code should be 200

  @positive
  Scenario: Successfully update a project name using an authentication token
    Given a registered user exists with name "Project Owner" and password "Pass12345!"
    And an authentication token is requested using the user credentials
    And a new project is created with name "Initial Project" using the authentication token
    When the project name is updated to "Renamed Project" using the authentication token
    Then the response status code should be 200
    And the project name should be "Renamed Project"

  @negative
  Scenario: Fail to update user with invalid token
    When the user updates their full name to "Hacker Name" using invalid token "invalid_token_999"
    Then the response status code should be 200
    And the response should contain error message "Not Authenticated"
    And the response should contain error code 102

  @negative
  Scenario: Fail to update user without authentication
    When a request to update user is sent without authentication
    Then the response status code should be 200
    And the response should contain error message "Not Authenticated"
    And the response should contain error code 102

  @negative
  Scenario: Fail to update project with invalid token
    When a project with ID 999999999 is updated to name "Hacked Name" using invalid token "invalid_token_999"
    Then the response status code should be 200
    And the response should contain error message "Not Authenticated"
    And the response should contain error code 102
