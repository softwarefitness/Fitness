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
Feature: Client Messaging and Discussion
@tag1
  Scenario: Send a message to a client
    Given I am logged in as an instructor
    And I have an enrolled client named "<client_name>"
    When I send a message saying "<message_content>" to the client
    Then the message should be delivered successfully
    And the client should be notified of the new message

  Scenario: Post a discussion in the forum
    Given I am logged in as an instructor
    And the program "<program_title>" has a discussion forum
    When I create a new post titled "<post_title>" with content "<post_content>"
    Then the post should appear in the forum for all enrolled clients
    And clients should be able to reply to the post

@tag2
Feature: Client Feedback and Progress Reports

  Scenario: Provide progress report to a client
    Given I am logged in as an instructor
    And I have an enrolled client named "<client_name>"
    When I create a progress report with the following details:
      | Report Date | <report_date> |
      | Progress    | <progress>    |
      | Notes       | <notes>       |
    Then the report should be saved successfully
    And the client should be notified of the new progress report

  Scenario: Provide feedback to a client
    Given I am logged in as an instructor
    And I have an enrolled client named "<client_name>"
    When I send feedback saying "<feedback_content>" to the client
    Then the feedback should be delivered successfully
    And the client should be notified
