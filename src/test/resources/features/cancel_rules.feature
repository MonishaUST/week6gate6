@api @db @negative
Feature: Cancelled orders cannot be cancelled again

  Scenario: Customer cancels the same order twice
    Given "alice" has a PLACED order
    When "alice" cancels that order for the first time
    Then the first cancellation response status is 200
    And the cancelled order status is "CANCELLED"
    When "alice" cancels the same order again
    Then the second cancellation response status is 409
    And the orders table has exactly one "CANCELLED" row for "alice"