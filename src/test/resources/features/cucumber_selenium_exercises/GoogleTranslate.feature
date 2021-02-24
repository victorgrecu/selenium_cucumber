@selenium @translate
Feature: As an internet user i want to be able to translate

  Scenario:Simple google search
    Given I am on google translate
    When I translate from "english" to "french"
    Then I get for:
    |expression|translation|
    |test      |test       |
    |game      |jeu        |



