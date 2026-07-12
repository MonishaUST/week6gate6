@api @negative
Feature: Out-of-stock products cannot be added to a cart

  Scenario: Customer tries to add an out-of-stock cap
    Given "alice" has a new API cart
    When she tries to add 1 x "SKU-CAP" to the API cart
    Then the add-to-cart API response status is 409