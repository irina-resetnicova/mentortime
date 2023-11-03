@UI @Smoke
Feature: User Registration Positive Flow

  @RegistrationUI @DBClean
  Scenario Outline: Successful Registration of a New User
    Given the User is on Register page
    And the User does not have any existing accounts
    When the User registers with the following details:
      | firstName   | lastName   | email   | password   |
      | <firstName> | <lastName> | <email> | <password> |
    Then the User is redirected to the page Your Account Has Been Created!
    And the inscription Your Account Has Been Created! appears on the screen
    And the User's registration is successful
    Examples:
      | firstName                        | lastName                         | email                                                                      | password             |
      | I2                               | Petrov254                        | email@gmail.com                                                            | 123658               |
      | IrinaIrinaIrinaIrinaIrinaIrinaIr | PetrovFirst254                   | emailemail@gmail.com                                                       | 123658               |
#      | Stas                             | P                                | gmailemailemail@gmail.com                                                  | 123658               |
#      | Kris                             | PetrovPetrovPetrovPetrovPetrovPe | emailemaill@gmail.com                                                      | 123658               |
#      | Irina                            | PetrovPetrovPetrovPetrovPetrov   | resetnicovaresetnicovaresetnicovaresetnicovaresetnicovaresetnico@gmail.com | 123658               |
#      | Olga                             | PetrovPetrovPetrovPetrovPetrv    | e@mail.ru                                                                  | 123658               |
#      | Nikol                            | Petrov                           | email1@mail.ru                                                             | 123658gryjisn*+%()%# |
#      | Tifany                           | Petrov                           | em@mail.ru                                                                 | 1234                 |

# special characters are accepted
# email max 74 characters
