@get @read @crud
Feature: Read Operations (GET)
  As a Todo.ly API consumer
  I want to retrieve users and projects using GET requests
  So that I can verify resource querying and access controls

  @positive
  Scenario: Successfully retrieve user profile details
    Given a registered user exists with name "Detail User" and password "Pass12345!"
    When the user profile is retrieved
    Then the user profile should have full name "Detail User"
    And the response status code should be 200

  @positive
  Scenario: Successfully retrieve an existing project by ID using an authentication token
    Given a registered user exists with name "Project Owner" and password "Pass12345!"
    And an authentication token is requested using the user credentials
    And a new project is created with name "Searchable Project" using the authentication token
    When the project is retrieved by ID using the authentication token
    Then the response status code should be 200
    And the retrieved project should match the created project

  @negative
  Scenario: Fail to retrieve user profile without authentication
    When a request to get user profile is sent without authentication
    Then the response status code should be 200
    And the response should contain error message "Not Authenticated"
    And the response should contain error code 102

  @negative
  Scenario: Fail to retrieve project without an authentication token
    When a project with ID 999999999 is requested without an authentication token
    Then the response status code should be 200
    And the response should contain error message "Not Authenticated"
    And the response should contain error code 102

  @negative
  Scenario: Fail to retrieve non-existent project by ID
    Given a registered user exists with name "Project Owner" and password "Pass12345!"
    And an authentication token is requested using the user credentials
    When a project with ID 999999999 is requested using the authentication token
    Then the response status code should be 200
    And the response should contain error message "You don't have access to this Project"
    And the response should contain error code 402
