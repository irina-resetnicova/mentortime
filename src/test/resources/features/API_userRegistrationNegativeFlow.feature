Feature: User Registration API Negative flow

  @Registration @Api @Negative
  Scenario: User can not  be registered successfully if the field password is empty
    Given The base URI is set to https: "https://reqres.in/"
    When POST request where password is empty is sent to the Server: "api/register"
    Then Get post-response ContentType JSON
    Then expected status code is 400

