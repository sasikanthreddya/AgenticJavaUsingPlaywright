Feature: E-Commerce Checkout Flow with GreenKart

  Scenario: Successfully Add Products and Place Order
    Given User navigates to GreenKart application
    When User adds "Cauliflower" product to cart
    And User adds "Tomato" product to cart
    And User proceeds to checkout
    And User clicks Place Order button
    And User selects "India" from country dropdown
    And User agrees to Terms and Conditions
    And User clicks Proceed button
    Then Order should be placed successfully
    And Cart should be empty
