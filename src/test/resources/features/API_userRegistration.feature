Feature: User Registration API
  Background:
    Given The base URI is set to https: "https://reqres.in/"

  @Registration @API @SmokeTest @Post
    Scenario: User can be registered successfully
    When POST request is sent to the Server: "api/register"
    Then Response code is 200
    And Get Post response
    And result is not null

  @Registration @API @SmokeTest @Post
  Scenario: User can be registered successfully (example 2)
    When POST request is sent to Server: "api/register"
    Then Response code is 200
    And Get the Post response

