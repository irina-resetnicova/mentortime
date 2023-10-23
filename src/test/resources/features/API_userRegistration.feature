Feature: User Registration API

  @Registration @API @SmokeTest @Post
    Scenario: User can be registered successfully
    Given The base URI is set to https: "https://reqres.in/"
    When POST request is sent to the Server: "api/register"
    Then Get Post response
    Then Response code is 200
    And result is not null
