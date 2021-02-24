@exercise
Feature: As a enthusiastic QA Engineer I want to have a sample feature file to learn cucumber

  @noparameters
  Scenario: Simple Dummy scenario for learning cucumber
    Given there is context
    When an event happens
    Then there is an outcome

  @parameters
  Scenario: You can use parameters to make your steps more robust and reusable
    Given you are using a search engine
    When you search after "test"
    Then you get a lot of results

  @parameters
  Scenario: You can use parameters to make your steps more robust and reusable
    Given you are using a search engine
    When you search after "selenium"
    Then you get a lot of results for "selenium" , "test automation" and "java"
    And at least 10 results for "webdriver"

  @parameters
  Scenario Outline: You can use parameters to make your steps more robust and reusable.
  If you have the same scenario with different parameters you may use Scenario Outline and Examples.
  You may have as many rows and columns in example as you want, but for simplicity I have one column and 3 rows.
  The first row is like the header of the table.
    Given you are using a search engine
    When you search after "<expression>"
    Then you get a lot of results
    Examples:
      | expression |
      | test       |
      | java       |
      | cucumber   |
