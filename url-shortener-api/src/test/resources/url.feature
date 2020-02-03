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
@UrlTest
Feature: Shorten URL
  the system should shorten the URL when the client passes a URL

  Scenario: Test shorten URL, The client send an URL for the system that return the URL shortened
    Given an URL Valid "http://www.google.com.br"
    When this URL is send for the system
    Then is returned an shortened URL
    
#    Examples:
#    | address                  |
#		 | http://www.google.com.br |
