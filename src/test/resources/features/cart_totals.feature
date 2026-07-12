@api @db @smoke
Feature: Cart totals are correct

  Scenario: Two bags have a total of 99800 paise
    Given "alice" creates an API cart with 2 x "SKU-BAG"
    Then the API cart total is 99800 paise
    And the database cart contains quantity 2 for SKU "SKU-BAG"