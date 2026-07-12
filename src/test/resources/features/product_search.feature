@ui @api @smoke
Feature: Product search is consistent between UI and API

  Scenario: Searching for bag returns Metro Carryall
    Given the ShopKart home page is open
    When the customer searches for "bag"
    Then the UI shows product "Metro Carryall"
    And GET products API for "bag" returns SKU "SKU-BAG" with name "Metro Carryall"