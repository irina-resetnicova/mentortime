@UI @Smoke
Feature: User Registration Negative Flow

  @UserRegistrationNegativeFlow
  Scenario Outline: User ca not be registered if first name does not confirm requirements
    Given User is on Register page
    And User does not have any account with <mail>
    When User registers with wrong firstname
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    Then User is not registered with entered <email>
    And A warning message <warning message> is appeared on the screen
    Examples:
      | firstName                            | lastName | email            | password | warning message                                 |
      | IrinaIrinaIrinaIrinaIrinaIrinaIriiuy | Petrov   | petrov@gmail.com | 1234567  | First Name must be between 1 and 32 characters! |
      | IrinaIrinaIrinaIrinaIrinaIrinaIri    | Petrov   | petrov@gmail.com | 1234567  | First Name must be between 1 and 32 characters! |

  @UserRegistrationWithExistingUser
  Scenario Outline: Registration with Existing User
    Given User is on Register page
    And User has already its account
    When User try to register with existing account <firstName>, <lastName>, <email>, <password>
    Then User is not registered with existing <email>
    And User does not relocated onto another page
    And Warning message Warning: E-Mail Address is already registered! is appeared on the screen
    Examples:
      | firstName | lastName | email          | password |
      | John      | Baiden   | john@gmail.com | 1234     |

# special characters are accepted
# email: max 74 characters
# defect: boundary password

