@multiple
Feature: Add to cart products one-by-one and delete after

  Scenario Outline: Add products to cart one-by-one and verify correct label is shown on Cart
    Given I stay in home page
    And "<search_name>" products are present in 'Buy now' search results
    When I add to cart first found product from the search results list
    Then I see the cart view page is opened
    And The cart label shows <number> count of products in it
    Examples: different types of goods adding to cart one-be-one
      | search_name       | number |
      | chinese green tee | 1      |
      | iphone 7s         | 2      |
      | iPad              | 3      |
