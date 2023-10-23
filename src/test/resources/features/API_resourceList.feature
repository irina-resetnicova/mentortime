Feature: List<RESOURCE>
  @UsersList @API @Get @SmokeTest @Regression
Scenario: List<RESOURCE> return data
    Given The base URI is set to https: "https://reqres.in/"
    When GET request is sent to server: "api/unknown"
    Then Get response ContentType JSON
    And Response code is 200

  Scenario:  List<RESOURCE> can be sorted by years
    Given The base URI is set to https: "https://reqres.in/"
    When GET request is sent to server: "api/unknown"
    Then Get response ContentType JSON
    And Response code is 200
    And List<RESOURCE> can be sorted by years
