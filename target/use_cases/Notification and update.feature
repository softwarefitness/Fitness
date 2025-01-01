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

@tag1
Feature: Notifications and Updates

  Scenario: Notify clients about program schedule changes
    Given I am logged in as an instructor
    And the program "<program_title>" has a schedule update
    When I notify all enrolled clients about the schedule change with the message "<notification_content>"
    Then all clients should receive the notification
    And the updated schedule should be visible in their program details

  Scenario: Announce new programs or special offers
    Given I am logged in as an instructor
    When I create an announcement for a new program titled "<program_title>" or a special offer "<offer_details>"
    Then all clients should receive the announcement
    And the details should appear in the notifications section for clients
