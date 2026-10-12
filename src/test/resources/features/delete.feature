@delete @crud
Feature: Delete Operations (DELETE)
  As a Todo.ly API consumer
  I want to delete users and projects using DELETE requests
  So that I can verify resource removal and deletion constraints

  @smoke @positive
  Scenario: Successfully delete a project using an authentication token
    Given a registered user exists with name "Project Owner" and password "Pass12345!"
    And an authentication token is requested using the user credentials
    And a new project is created with name "To Be Deleted" using the authentication token
    When the project is deleted using the authentication token
    Then the project should be marked as deleted

  @positive
  Scenario: Successfully delete user account
    Given a registered user exists with name "Delete User" and password "Pass12345!"
    When the current user account is deleted
    Then the user account should no longer exist

  @negative
  Scenario: Fail to delete user without authentication
    When a request to delete user is sent without authentication
    Then the response status code should be 200
    And the response should contain error message "Not Authenticated"
    And the response should contain error code 102

  @negative
  Scenario: Fail to delete user with invalid token
    When a request to delete user is sent using invalid token "invalid_token_999"
    Then the response status code should be 200
    And the response should contain error message "Not Authenticated"
    And the response should contain error code 102

  @negative
  Scenario: Fail to delete project with invalid ID
    Given a registered user exists with name "Project Owner" and password "Pass12345!"
    And an authentication token is requested using the user credentials
    When a project with ID 999999999 is deleted using the authentication token
    Then the response status code should be 200
    And the response should contain error message "Invalid Id"
    And the response should contain error code 301

  @negative
  Scenario: Fail to delete project with invalid token
    When a project with ID 999999999 is deleted using invalid token "invalid_token_999"
    Then the response status code should be 200
    And the response should contain error message "Not Authenticated"
    And the response should contain error code 102
