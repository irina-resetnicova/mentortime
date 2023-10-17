Feature: User Registration Negative Flow

  @UserRegistrationNegativeFlow
    @UI
  Scenario Outline: User ca not be registered if first name does not confirm requirements
    Given User is on Register page
    And User does not have account
    When User fills firstName field <firstName>
    Then User is not registered
    And A warning message <warning message> is appeared on the screen
#    And Get the fields clear

    Examples:
      | firstName                         | warning message                                 |
#      |                                   | First Name must be between 1 and 32 characters! |
      | IrinaIrinaIrinaIrinaIrinaIrinaIri | First Name must be between 1 and 32 characters! |


  @UserRegistrationWithExistingUser
  Scenario Outline: Registration with Existing User


    Given User is on Register page
    And User has already its account
    When User registers
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

