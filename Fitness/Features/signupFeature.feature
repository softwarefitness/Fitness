# Author: loaa
@tag
Feature: Sign-up
Sign-up should be quick and user-friendly.
  @tag1
  Scenario: Register customer successful

    Given there is a customer with customer ID "id" ,NAME "customer name" , PASSWORD "pass " , phone "phone", address "address"
    When the customer is registered "customer name"

  @tag2
  Scenario: a customer that is already registered

    Given there is a customer with customer ID "id" ,NAME "customer name" , PASSWORD "pass " , phone "phone", address "address"
    When the customer is registered "customer name"