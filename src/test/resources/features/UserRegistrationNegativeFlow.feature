@UI @Smoke
Feature: User Registration Negative Flow

  @UserRegistrationNegativeFlow
  Scenario Outline: User ca not be registered if first name does not meet requirements
    Given the User is on Register page
    And the User does not have any account with <mail>
    When the User registers with the wrong firstname
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    Then the User is not registered with <email>
    And a warning message <warning message> is displayed on the screen
    Examples:
      | firstName                            | lastName | email            | password | warning message                                 |
      | IrinaIrinaIrinaIrinaIrinaIrinaIriiuy | Petrov   | petrov@gmail.com | 1234567  | First Name must be between 1 and 32 characters! |
      | IrinaIrinaIrinaIrinaIrinaIrinaIri    | Petrov   | petrov@gmail.com | 1234567  | First Name must be between 1 and 32 characters! |

  @UserRegistrationWithExistingUser
  Scenario Outline: Registration with Existing User
    Given the User is on Register page
    And the User already has an account
    When the User tries to register with an existing account <firstName>, <lastName>, <email>, <password>
    Then the User is not registered with the existing <email>
    And the User is not relocated to another page
    And an alert message Warning: E-Mail Address is already registered! is displayed on the screen
    Examples:
      | firstName | lastName | email          | password |
      | John      | Baiden   | john@gmail.com | 1234     |

# special characters are accepted
# email: max 74 characters
# defect: boundary password

