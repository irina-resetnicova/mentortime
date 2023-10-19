Feature: User Registration Negative Flow

  @UserRegistrationNegativeFlow
    @UI@CleanDB
  Scenario Outline: User ca not be registered if first name does not confirm requirements
    Given User is on Register page
    And User does not have account
    When User fills firstName field <firstName>
    Then User is not registered
    And A warning message <warning message> is appeared on the screen
    And Get the fields clear
    And User is on Register page


    Examples:
      | firstName                         | warning message                                 |
      |                                   | First Name must be between 1 and 32 characters! |
#      | IrinaIrinaIrinaIrinaIrinaIrinaIri | First Name must be between 1 and 32 characters! |


  @UserRegistrationWithExistingUser @UI
  Scenario: Registration with Existing User
    Given User is on Register page
    And User has already its account
    When User try to register with existing account
    Then User is not registered
    And Warning message <Warning: E-Mail Address is already registered!> is appeared on the screen


# special characters are accepted
# email: max 74 characters
# defect: boundary password

