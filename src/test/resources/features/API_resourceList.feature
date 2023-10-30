Feature: List<RESOURCE>
  Background:
    Given The base URI is set to https: "https://reqres.in/"

  @UsersList @API @SmokeTest @Regression
Scenario: List<RESOURCE> return data
    When GET request is sent to server: "api/unknown"
    Then Response code is 200
    And Get response ContentType JSON

  @UsersList @API @SmokeTest @Regression
  Scenario:  List<RESOURCE> can be sorted by years
    When GET request is sent to server: "api/unknown"
    Then Response code is 200
    And Get response ContentType JSON
    And List<RESOURCE> can be sorted by years
