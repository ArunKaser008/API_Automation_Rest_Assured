Feature: Get Users

  Scenario: Verify users can be fetched

    Given User API is initialized

    When I request page 2 users

    Then response status should be 200

    And total users should be greater than 0