#Author: mr.amitksharma@gmail.com
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

@DevTest
Feature: Test following features of Statistics API
  Description : This feature file will focus on testing multiple features provided by statistics API.
  a) Connectivity with REST API.
  b) HTTP Status Codes handled in REST implementation.
  c) Data validation implemented in REST
  d) Processing Logic implemented in REST as per business logic 
  e) Media Types handled in REST implementation.
  f) Error handling in API

  
  Background:
   Given REST-API end point is available as baseURL

 
   Scenario: Test response status 200 for given API end point
    When Connection is tried to reach out with given request
    Then Response is returned
    And  status code is 200
    
   Scenario: Test data pull with status 200 for given API end point with data set
   	When Connection is tried to reach out with given request
    Then Response is returned
    And  status code is 200
    And  stats are matching
    