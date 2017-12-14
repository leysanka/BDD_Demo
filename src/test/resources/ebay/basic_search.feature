Feature: Simple search in Home page

  Scenario: Navigate to home page and perform search
    Given I stay in home page
    When  I search for "xiaomi"
    Then  Search results are found
