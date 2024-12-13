#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template


@tag
Feature: Client Instruction Management
  I want to manage the client instructions effectively for better interaction and feedback.
 
 <!--Create Case --> 
@tag1
 Scenario: Create new fitness program
  Given I am logged in as an instructor
  When I create a new program with the following details:
    | Program Title     | <program_title>      |
    | Duration          | <duration>           |
    | Difficulty Level  | <difficulty_level>   |
    | Goals             | <goals>              |
    | Price             | <price>              |
    | Materials         | <materials>          |
  Then The program should be created successfully
  And The program details should be visible to clients
    
    
  @tag2
  Scenario: Create a fitness program with multiple attachments (videos, images, documents)
    Given I am logged in as an instructor
    When I create a fitness program titled "<program_title>" and attach videos, images, and documents to the program
    Then the program should be created successfully with all attached media visible to clients
    
    
  @tag3
  Scenario: Create a fitness program with missing details
    Given I am logged in as an instructor
    When I attempt to create a fitness program titled "<program_title>" without entering a price
    Then I should receive an error stating that the price is required
    And the program should not be created
    
    
  @tag4
  Scenario: Create a program with unsupported difficulty level
    Given I am logged in as an instructor
    When I attempt to create a fitness program titled "<program_title>" with the difficulty level "<difficulty_level>"
    Then I should receive an error stating that the difficulty level is unsupported
    And the program should not be created
    


<!--Update Case -->
@tag5
Scenario: Update an existing fitness program
  Given I am logged in as an instructor
  And I have an existing fitness program titled "<program_title>"
  When I update the program with the following details:
    | Duration          | <new_duration>       |
    | Difficulty Level  | <new_difficulty_level> |
    | Price             | <new_price>          |
    | Materials         | <new_materials>      |
  Then The program should be updated successfully
  And The updated details should be visible to clients
  
<!--delete Case --> 
@tag6
    Scenario: Delete a fitness program
  Given I am logged in as an instructor
  And I have an existing fitness program titled "<program_title>"
  When I delete the fitness program
  Then The program should be deleted successfully
  And The program should no longer be visible to clients   
  
    