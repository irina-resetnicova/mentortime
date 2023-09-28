Feature: User Registration Positive Flow

  @Registration
  Scenario Outline: A new User is successfully registered
    Given User is on the Home page
    And User is not registered
    When User goes on Register page
    And User fills firstName <firstName>
    And User fills lastName <lastName>
    And User fills email <email>
    And User fills password <password>
    And User clicks on newsletter
    And User clicks on agreementOfThePrivacyPolicy
    And User clicks on btn continue
    Then User is registered
    And User is relocated on the page "Your Account Has Been Created!"
    And The inscription "Your Account Has Been Created!" is appeared on the screen


    Examples:
      | firstName                         | lastName                         | email                                                                      | password             |
      | I                                 | Petrov254                        | email@gmail.com                                                            | 123658               |
      | IrinaIrinaIrinaIrinaIrinaIrinaIri | PetrovFirst254                   | emailemail@gmail.com                                                       | 123658               |
      | Irina                             | P                                | gmailemailemail@gmail.com                                                  | 123658               |
      | Irina                             | PetrovPetrovPetrovPetrovPetrovPe | emailemail@gmail.com                                                       | 123658               |
      | Irina                             | PetrovPetrovPetrovPetrovPetrov   | resetnicovaresetnicovaresetnicovaresetnicovaresetnicovaresetnico@gmail.com | 123658               |
      | Irina                             | PetrovPetrovPetrovPetrovPetrov   | e@mail.ru                                                                  | 123658               |
      | Irina                             | Petrov                           | email1@mail.ru                                                             | 123658gryjisn*+%()%# |
      | Irina                             | Petrov                           | em@mail.ru                                                                 | 1234                 |

# special characters are accepted
# email max 74 characters

  @UserRegistrationWithExistingUser
  Scenario Outline: Registration with Existing User

    Given User is on the Home page
    And User is already registered
    When User goes on Register page
    And User fills firstName <firstName>
    And User fills lastName <lastName>
    And User fills email <email>
    And User fills password <password>
    And User clicks on newsletter
    And User clicks on agreementOfThePrivacyPolicy
    And User clicks on btn continue
    Then User is not registered
    And A warning message <warning message> is appeared on the screen


    Examples:
      | firstName                         | lastName                          | email           | password              |
      |                                   | Petrov254                         | email@gmail.com | 123658                |


# special characters are accepted
# email: max 74 characters
# defect: boundary password




