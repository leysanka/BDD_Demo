@multiple
Feature: Add goods one-by-one and delete after

  Scenario Outline: Add products to cart one-by-one and verify correct label is shown on Cart
    Given I stay in home page
    And "<a>" products are found in BuyNow
    When I add to cart product number <b> from the list of items shown in the Search Results
    Then I see the cart view page is opened
    And The cart label shows <c> count of products in it
    Examples: different type goods
      |a|b|c|
      |chinese green tee| 2 | 1 |
      |iphone 7s| 50 | 2 |
      |iPad| 12 | 3 |
