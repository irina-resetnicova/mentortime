Feature: User Registration Negative Flow

  @UserRegistrationNegativeFlow @UI
  Scenario Outline: User ca not be registered if first name does not confirm requirements
    Given User is on Register page
    And User does not have any account with petrov@gmail.com
    When User registers with wrong firstname
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    Then User is not registered with entered credential
    And A warning message <warning message> is appeared on the screen
    Examples:
      | firstName                            | lastName | email            | password | warning message                                 |
      | IrinaIrinaIrinaIrinaIrinaIrinaIriiuy | Petrov   | petrov@gmail.com | 1234567  | First Name must be between 1 and 32 characters! |
      | IrinaIrinaIrinaIrinaIrinaIrinaIri    | Petrov   | petrov@gmail.com | 1234567  | First Name must be between 1 and 32 characters! |

# Baiden
  @UserRegistrationWithExistingUser @UI
  Scenario: Registration with Existing User
    Given User is on Register page
    And User has already its account
    When User try to register with existing account
    Then User is not registered
    And User does not relocated onto another page
    And Warning message Warning: E-Mail Address is already registered! is appeared on the screen

# special characters are accepted
# email: max 74 characters
# defect: boundary password

