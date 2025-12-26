@hooks-test
Feature: Hook Testing
  Test that hooks execute correctly

  @smoke
  Scenario: Test basic hooks
    Given I verify hooks are working
    When I perform an action
    Then I should see the result

  @database
  Scenario: Test database hooks
    Given the database is initialized
    When I query for data
    Then I should get results

  @slow @headless
  Scenario: Test tagged hooks
    Given I am in headless mode
    When I wait for slow operation
    Then I should complete successfully