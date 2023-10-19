Feature: User Registration Positive Flow

  @Registration@UI@CleanDB@
  Scenario Outline: A new User is successfully registered
    Given User is on Register page
    And User does not have account
    When User registers
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    Then User is registered
    And User is relocated on the page Your Account Has Been Created!
    And The inscription Your Account Has Been Created! is appeared on the screen
    And User is on Register page

    Examples:
      | firstName                         | lastName                         | email                                                                      | password             |
      | I2                                | Petrov254                        | email@gmail.com                                                            | 123658               |
#      | IrinaIrinaIrinaIrinaIrinaIrinaIri | PetrovFirst254                   | emailemail@gmail.com                                                       | 123658               |
#      | Irina                             | P                                | gmailemailemail@gmail.com                                                  | 123658               |
#      | Irina                             | PetrovPetrovPetrovPetrovPetrovPe | emailemail@gmail.com                                                       | 123658               |
#      | Irina                             | PetrovPetrovPetrovPetrovPetrov   | resetnicovaresetnicovaresetnicovaresetnicovaresetnicovaresetnico@gmail.com | 123658               |
#      | Irina                             | PetrovPetrovPetrovPetrovPetrov   | e@mail.ru                                                                  | 123658               |
#      | Irina                             | Petrov                           | email1@mail.ru                                                             | 123658gryjisn*+%()%# |
#      | Irina                             | Petrov                           | em@mail.ru                                                                 | 1234                 |

# special characters are accepted
# email max 74 characters








