Feature: User Registration Negative Flow

  @UserRegistrationNegativeFlow
  Scenario Outline: Registration a new User

    Given User is on the Home page
    And User is not registered
    When User presses My Account btn
    And Register from dropDown
    And User goes on Register page
    And User registers
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    Then User is not registered
    And A warning message <warning message> is appeared on the screen
    Examples:
      | firstName                         | lastName                          | email       | password              | warning message                                 |
      |                                   | Petrov254                         | randomEmail | 123658                | First Name must be between 1 and 32 characters! |
      | IrinaIrinaIrinaIrinaIrinaIrinaIri | Petrov254                         | randomEmail | 123658                | First Name must be between 1 and 32 characters! |
      | Irina                             |                                   | randomEmail | 123658                | Last Name must be between 1 and 32 characters!  |
      | Irina                             | IrinaIrinaIrinaIrinaIrinaIrinaIri | randomEmail | 123658                | Last Name must be between 1 and 32 characters!  |
      | Alex1235                          | Petrov254                         |             | 123658                | E-Mail Address does not appear to be valid!     |
      | Alex1235                          | Petrov254                         |             | 123658                | E-Mail Address does not appear to be valid!     |
      | Alex1235                          | Petrov254                         |             |                       | Password must be between 4 and 20 characters!   |
      | Alex1235                          | Petrov254                         |             | 123                   | Password must be between 4 and 20 characters!   |
      | Alex1235                          | Petrov254                         |             | 123658gryjisn*+%()%#! | Password must be between 4 and 20 characters!   |



  @UserRegistrationWithExistingUser
  Scenario Outline: Registration with Existing User

    Given User is on the Home page
    And User is already registered
    When User presses My Account btn
    And Register from dropDown
    And User goes on Register page
    And User registers
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    Then User is not registered
    And A warning message <warning message> is appeared on the screen
    Examples:
      | firstName | lastName | email           | password |
      | Ira       | Ira      | email@gmail.com | 123456   |

# special characters are accepted
# email: max 74 characters
# defect: boundary password

