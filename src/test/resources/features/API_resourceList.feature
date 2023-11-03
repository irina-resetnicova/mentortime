@UsersList @API @SmokeTest @Regression
Feature: List<RESOURCE>
  Background:
    Given the base URI is set to  "https://reqres.in/"

@GetListOfResources
Scenario: Retrieving Data from List of Resources //Scenario: List<RESOURCE> return data
    When a GET request is sent to the server with the endpoint "api/unknown"
    Then the response code should be 200
    And the response is retrieved and displayed on the screen

  @SortListOfResources
  Scenario: Sorting List of Resources by Years
    When a GET request is sent to the server with the endpoint "api/unknown"
    Then the response code should be 200
    And the response is retrieved and displayed on the screen
    And the List of Resources can be sorted by years
