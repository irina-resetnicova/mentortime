Feature: User Registration API Negative flow
  Background:
    Given The base URI is set to https: "https://reqres.in/"

  @Registration @Api @Negative
  Scenario: User can not  be registered successfully if the field password is empty
    When POST request where password is empty is sent to the Server: "api/register"
    Then expected status code is 400
    And Get post-response ContentType JSON

