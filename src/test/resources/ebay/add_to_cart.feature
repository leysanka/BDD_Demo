Feature: add to cart products as a not logged in user

#Scenario: Navigate to home page and perform search
#    Given I stay in home page
#    When I search for "chinese green tee"
#    Then Search results are found
  Background:
    Given The cart is empty

  Scenario: Add two products to cart
    Given The "chinese green tee" product has already been added to cart
    And The cart label shows 1 count of products in it
    And "iPad" products are found in BuyNow
    When I add to cart product number 4 from the list of items shown in the Search Results
    Then I see the cart view page is opened
    And The cart label shows 2 count of products in it


  Scenario Outline: Add one product to cart
#    Given The cart is empty
    And "<a>" products are found in BuyNow
    When I add to cart product number <b> from the list of items shown in the Search Results
    Then I see the cart view page is opened
    And The cart label shows 1 count of products in it
    Examples: different type goods
      |a|b|
      |chinese green tee| 2 |
      |iphone 7s| 50 |
