Feature: add to cart products as a not logged in user

Scenario: Navigate to home page and perform search
    Given I stay in home page
    When I search for "chinese green tee"
    Then Search results are found

  Scenario: Add 1 product to cart
    Given "chinese green tee" products are found in BuyNow
    And The cart is empty
    When I add to cart product number <2> from the list of items shown in the Search Results page
    Then I see the cart view page is opened
    And Selected product is shown there
    And The cart label shows <1> count of products in it

#  Scenario: Add to cart one more product
#    Given <chinese green tee> products are found in BuyNow
#    And The cart label shows <1> count of products in it
#    When I add to cart product number <4> from the list of items shown in the Search Results page
#    Then I see the cart view page is opened
#    And Selected product is shown there
#    And The cart label shows <2> count of products in it


# Scenario: Add another item to cart from Cart view
#   Given Cart view is shown
#   And The cart label shows <1> count of products in it
#   And <chinese green tee> products are found in BuyNow
#   When I add to cart product number <2> from the list of items shown in the Search Results page
#   Then I see the cart view page is opened
#   And The cart label shows <2> count of products in it



