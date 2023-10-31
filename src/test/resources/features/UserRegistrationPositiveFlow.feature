Feature: User Registration Positive Flow

  @RegistrationUI @UI @DBClean
  Scenario Outline: A new User is successfully registered
    Given User is on Register page
    And User does not have any ACCOUNTS
    When User registers
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    Then User is registered
    And User is relocated on the page Your Account Has Been Created!
    And The inscription Your Account Has Been Created! is appeared on the screen
    And new cycle
    Examples:
      | firstName                        | lastName                         | email                                                                      | password             |
      | I2                               | Petrov254                        | email@gmail.com                                                            | 123658               |
      | IrinaIrinaIrinaIrinaIrinaIrinaIr | PetrovFirst254                   | emailemail@gmail.com                                                       | 123658               |
      | Stas                             | P                                | gmailemailemail@gmail.com                                                  | 123658               |
      | Kris                             | PetrovPetrovPetrovPetrovPetrovPe | emailemaill@gmail.com                                                      | 123658               |
      | Irina                            | PetrovPetrovPetrovPetrovPetrov   | resetnicovaresetnicovaresetnicovaresetnicovaresetnicovaresetnico@gmail.com | 123658               |
      | Olga                             | PetrovPetrovPetrovPetrovPetrv    | e@mail.ru                                                                  | 123658               |
      | Nikol                            | Petrov                           | email1@mail.ru                                                             | 123658gryjisn*+%()%# |
      | Tifany                           | Petrov                           | em@mail.ru                                                                 | 1234                 |

# special characters are accepted
# email max 74 characters
