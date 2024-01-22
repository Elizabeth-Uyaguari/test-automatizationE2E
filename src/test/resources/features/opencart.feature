@BuyShop
Feature: Shop Cart Automatization

  Scenario Outline: shop products
    Given Elizabeth go to main page
    When select product
    Then add product to cart
    And back to main page
    And select second product
    And select product color
    Then add product to cart again
    Then go to shop cart
    And go to checkout
    And select guest checkout
    And continue to billing details
    And fill out personal information "<firstname>", "<lastname>", "<email>", "<phone>"
    And fill out address information "<address1>", "<city>", "<cp>"
    And continue to delivery method
    And continue to Payment Method
    And select terms and conditions and continue to confirm order
    And confirm order
    And verify order

    Examples:
      | firstname | lastname | email                 | phone       | address1  | city   | cp     |
      | Elizabeth | Uyaguari | ely.uyaguar@gmail.com | 09083625445 | Orquideas | Cuenca | 010101 |