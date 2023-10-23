Feature: List of Users
  @UsersList @API @Get @SmokeTest @Regression
    Scenario: Getting a list of users is reflected on the screen
    Given the base URI is set to https: "https://reqres.in/"
    When GET request is sent to server: "api/users?page=2"
    Then Get response ContentType JSON
    And Response code is 200

  @UsersList @API @Post @SmokeTest @Regression
  Scenario: All users have emails end on reqres.in
    Given The base URI is set to https: "https://reqres.in/"
    When GET request is sent to server: "api/users?page=2"
    Then Get response ContentType JSON
    And Response code is 200
    And All users have emails end on reqres.in

  @UsersList @API @Post @SmokeTest @Regression
  Scenario: Display all avatars from the list of users
    Given The base URI is set to https: "https://reqres.in/"
    When GET request is sent to server: "api/users?page=2"
    Then Get response ContentType JSON
    And Response code is 200
    And Display all avatars from the list




