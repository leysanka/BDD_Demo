Feature: Add to cart products as a not logged in user

  @SeverityLevel.BLOCKER
  Scenario: Add two products to cart
    Given The "chinese green tee" product has already been added to cart
    And The cart label shows 1 count of products in it
    And "iPad" products are present in 'Buy now' search results
    When I add to cart first found product from the search results list
    Then I see the cart view page is opened
    And The cart label shows 2 count of products in it


  @SeverityLevel.NORMAL
  Scenario Outline: Add one product to cart
    Given The cart is empty
    And "<search_name>" products are present in 'Buy now' search results
    When I add to cart first found product from the search results list
    Then I see the cart view page is opened
    And The cart label shows 1 count of products in it
    Examples: different types of goods
      | search_name       |
      | chinese green tee |
      | iphone 7s         |
