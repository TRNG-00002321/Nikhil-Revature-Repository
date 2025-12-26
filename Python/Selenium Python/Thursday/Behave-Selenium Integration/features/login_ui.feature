@login @ui
Feature: Login UI Tests
  As a user
  I want to log in to the application
  So that I can access secure features

  Background:
    Given the browser is on the login page

  @smoke @positive
  Scenario: Successful login redirects to secure area
    When I enter username "tomsmith"
    And I enter password "SuperSecretPassword!"
    And I click the login button
    Then I should be on the secure area page
    And I should see message containing "You logged into"

  @negative
  Scenario: Invalid password shows error
    When I enter username "tomsmith"
    And I enter password "wrongpassword"
    And I click the login button
    Then I should remain on the login page
    And I should see error containing "Your password is invalid"

  @negative
  Scenario: Invalid username shows error
    When I enter username "invaliduser"
    And I enter password "SuperSecretPassword!"
    And I click the login button
    Then I should remain on the login page
    And I should see error containing "Your username is invalid"

  @logout
  Scenario: Logout returns to login page
    Given I am logged in as "tomsmith"
    When I click logout
    Then I should be on the login page
    And I should see message containing "You logged out"