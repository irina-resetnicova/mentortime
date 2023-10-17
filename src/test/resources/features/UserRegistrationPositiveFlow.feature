Feature: User Registration Positive Flow

  @Registration
  @Severity(SeverityLevel.BLOCKER)
  @Issue("OpenCart")
  @Owner("IrinaResetnicova")
  Scenario Outline: A new User is successfully registered
    Given User is on the Home page
    And User is not registered
    When User presses My Account btn
    And Register link from dropDown
    And User goes on Register page
    And User registers
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    Then User is registered
    And User is relocated on the page Your Account Has Been Created!
    And The inscription "Your Account Has Been Created!" is appeared on the screen
    And User presses My Account btn
    And Register link from dropDown

    Examples:
      | firstName                         | lastName                         | email                                                                      | password             |
      | I2                                | Petrov254                        | email@gmail.com                                                            | 123658               |
      | IrinaIrinaIrinaIrinaIrinaIrinaIri | PetrovFirst254                   | emailemail@gmail.com                                                       | 123658               |
      | Irina                             | P                                | gmailemailemail@gmail.com                                                  | 123658               |
      | Irina                             | PetrovPetrovPetrovPetrovPetrovPe | emailemail@gmail.com                                                       | 123658               |
      | Irina                             | PetrovPetrovPetrovPetrovPetrov   | resetnicovaresetnicovaresetnicovaresetnicovaresetnicovaresetnico@gmail.com | 123658               |
      | Irina                             | PetrovPetrovPetrovPetrovPetrov   | e@mail.ru                                                                  | 123658               |
      | Irina                             | Petrov                           | email1@mail.ru                                                             | 123658gryjisn*+%()%# |
      | Irina                             | Petrov                           | em@mail.ru                                                                 | 1234                 |

# special characters are accepted
# email max 74 characters








