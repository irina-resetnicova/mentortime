@Registration @API @SmokeTest
Feature: User Registration API
  Background:
    Given the base URI is set to  "https://reqres.in/"

  @RegistrationExample1
    Scenario: Successful User Registration (example 1: Map)
    When a POST request is sent to the Server with the endpoint "api/register"
    Then the response code should be 200
    And the Post response is obtained
    And the result is not null

  @RegistrationExample2
  Scenario: Successful User Registration (example 2: POJO file)
    When a POST request is sent to the Server: "api/register"
    Then the response code should be 200
    And the Post the response is retrieved

