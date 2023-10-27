Feature: List of Users
  Background:
    Given The base URI is set to https: "https://reqres.in/"

  @UsersList @API @Get @SmokeTest @Regression
    Scenario: Getting a list of users is reflected on the screen
    When GET request is sent to server: "api/users?page=2"
    Then Response code is 200
    And Get response ContentType JSON


  @UsersList @API @Post @SmokeTest @Regression
  Scenario: All users have emails end on reqres.in
    When GET request is sent to server: "api/users?page=2"
    Then Response code is 200
    And Get response ContentType JSON
    And All users have emails end on reqres.in

  @UsersList @API @Post @SmokeTest @Regression
  Scenario: Display all avatars from the list of users
    When GET request is sent to server: "api/users?page=2"
    Then Response code is 200
    And Get response ContentType JSON
    And Display all avatars from the list




