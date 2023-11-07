Feature: User Registration API Negative flow
  Background:
    Given the base URI is set to  "https://reqres.in/"

  @Registration @API @Negative
  Scenario: User Cannot Be Registered Successfully with an Empty Password
    When a POST request with an empty password is sent to the server with the endpoint "api/register"
    Then the expected status code should be 400
    And the Post response is obtained and displayed

