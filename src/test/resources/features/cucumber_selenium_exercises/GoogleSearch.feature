@selenium
Feature: As an internet user i want to be able to get results for every search

  Scenario:Simple google search
    Given I am on google search page
    When I search after "test"
    Then I get results

  Scenario Outline:Simple google search with scenario outline
    Given I am on google search page
    When I search after "<search>"
    Then I get results
    Examples:
      | search   |
      | cucumber |
      | selenium |
      | java     |