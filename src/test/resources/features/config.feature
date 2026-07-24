Feature: Framework Configuration Validation

  As a framework developer
  I want to validate the configuration module
  So that API tests use the correct environment settings

  Scenario: Load QA configuration successfully
    Given the framework configuration is initialized
    Then the base URL should not be empty
    And the connection timeout should be greater than 0
    And the read timeout should be greater than 0