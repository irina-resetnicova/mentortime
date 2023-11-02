@Registration @API @SmokeTest
Feature: User Registration API
  Background:
    Given the base URI is set to  "https://reqres.in/"

  @Example1
    Scenario: Successful User Registration
    When a POST request is sent to the Server with the endpoint "api/register"
    Then the response code should be 200
    And the Post response is obtained
    And the result is not null

  @Example2
  Scenario: Successful User Registration (example 2)
    When a POST request is sent to the Server with the endpoint "api/register"
    Then the response code should be 200
    And the Post the response is retrieved

