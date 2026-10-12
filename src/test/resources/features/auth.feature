@auth @crud
Feature: Authentication Operations (AUTH)
  As a Todo.ly API consumer
  I want to request and validate authentication tokens
  So that I can verify token generation and authentication security

  @smoke @positive
  Scenario: Successfully generate authentication token with valid user credentials
    Given a registered user exists with name "Token User" and password "Pass12345!"
    When an authentication token is requested using the user credentials
    Then the token should be successfully generated
    And the response status code should be 200

  @negative
  Scenario: Fail to generate token with non-existent user email
    When an authentication token is requested with email "nonexistent_999999@test.com" and password "Pass12345!"
    Then the response status code should be 200
    And the response should contain error message "Account doesn't exist"
    And the response should contain error code 105

  @negative
  Scenario: Fail to generate token with incorrect password
    Given a registered user exists with name "Valid User" and password "Pass12345!"
    When an authentication token is requested with incorrect password "WrongPassword123!"
    Then the response status code should be 200
    And the response should contain error message "Not Authenticated"
    And the response should contain error code 102

  @negative
  Scenario: Fail to generate token without authentication credentials
    When an authentication token is requested without authentication credentials
    Then the response status code should be 200
    And the response should contain error message "Not Authenticated"
    And the response should contain error code 102
