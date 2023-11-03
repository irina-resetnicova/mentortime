@UsersList @API @Get @SmokeTest @Regression
Feature: List of Users
  Background:
    Given the base URI is set to  "https://reqres.in/"

  @GetListOfUsers
    Scenario: Displaying User List
    When a GET request is sent to the server with the endpoint "api/users?page=2"
    Then the response code should be 200
    And the response is retrieved and displayed on the screen

  @GetEmails
  Scenario: Verifying that all User Emails end on reqres.in
    When a GET request is sent to the server with the endpoint "api/users?page=2"
    Then the response code should be 200
    And the response is retrieved and displayed on the screen
    And all users have emails end on reqres.in

  @GetAvatars
  Scenario: Displaying Avatars of Users
    When a GET request is sent to the server with the endpoint "api/users?page=2"
    Then the response code should be 200
    And the response is retrieved and displayed on the screen
    And  all avatars are displaying on the screen from the list




