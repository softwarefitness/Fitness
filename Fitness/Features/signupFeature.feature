# Author: loaa
@tag
Feature: Sign-up
Sign-up should be quick and user-friendly.

  @tag1
  Scenario: Successful Sign-up
  Admin can register a user successfully by providing required details.

    Given there is a user with user ID "id", NAME "user name", PASSWORD "pass", phone "phone", and address "address"
    When the user is registered with NAME "user name"
    Then the user should be added successfully to the system

  @tag2
  Scenario: Successful Sign-up Message
  A new user should receive a confirmation email and a personalized greeting upon signing in.

    Given I have chosen to sign up 
    When I sign up with valid details 
    Then I should receive a confirmation email
    And I should see a personalized greeting message

  @tag3
  Scenario: Duplicate Email
  A user should not be able to sign up with an email address that is already registered.

    Given I have chosen to sign up 
    When I sign up with an email address that is already registered 
    Then I should be told that the email is already registered 
    And I should be offered the option to recover my password 

  @tag4
  Scenario: Weak Password
  A user should not be able to sign up with a weak password.

    Given I have chosen to sign up
    When I sign up with a password that is less than 8 characters, does not include a special character, or contains spaces
    Then I should be told that my password is too weak
    And I should be prompted to choose a password that:
      | Criteria                           |
      | Has at least 8 characters          |
      | Includes at least one special char |
      | Contains no spaces                 |

  @tag5
  Scenario: Missing Required Fields
  A user should be notified when required fields are left empty.

    Given I have chosen to sign up
    When I leave required fields empty
    Then I should be notified that all fields are required

  @tag6
  Scenario: Invalid Email Format
  A user should not be able to sign up with an improperly formatted email address.

    Given I have chosen to sign up
    When I enter an email address that:
      | Invalid Cases                          |
      | Does not contain an "@" symbol         |
      | Does not have a domain after "@"       |
      | Lacks a valid top-level domain (e.g., .com, .net) |
      | Contains spaces                        |
      | Has special characters not allowed in emails (e.g., #, %, &) |
    Then I should be notified that my email address is invalid
    And I should be prompted to enter a valid email address in the format:
      | Valid Format Example        |
      | username@domain.com         |
