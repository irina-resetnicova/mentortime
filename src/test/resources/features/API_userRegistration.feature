Feature: User Registration API

  @Registration @API @SmokeTest @Post
    Scenario: User can be registered successfully
    Given the base URI is set to https: "https://reqres.in/"
    When POST request is sent to the Server: "api/register"
    Then status-code 200 appears on the screen
    And result is not null
