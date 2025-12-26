@registration
Feature: User Registration
  As a new user
  I want to register for an account
  So that I can access the application features

  @smoke @positive
  Scenario: Successful registration with valid details
    Given I am on the registration page
    When I enter my name "John Doe"
    And I enter my email "john.doe@example.com"
    And I enter my password "SecurePass123!"
    And I confirm my password "SecurePass123!"
    And I click the register button
    Then I should see a success message
    And I should receive a verification email

  @negative
  Scenario: Registration fails with mismatched passwords
    Given I am on the registration page
    When I enter my name "Jane Doe"
    And I enter my email "jane.doe@example.com"
    And I enter my password "SecurePass123!"
    And I confirm my password "DifferentPass456!"
    And I click the register button
    Then I should see an error "Passwords do not match"

  @negative
  Scenario: Registration fails with invalid email
    Given I am on the registration page
    When I enter my name "Bob Smith"
    And I enter my email "invalid-email"
    And I enter my password "SecurePass123!"
    And I confirm my password "SecurePass123!"
    And I click the register button
    Then I should see an error "Invalid email format"

  @data-driven
  Scenario Outline: Password validation rules
    Given I am on the registration page
    When I enter my password "<password>"
    And I click the register button
    Then I should see validation message "<message>"

    Examples:
      | password    | message                        |
      | short       | Password must be 8+ characters |
      | nodigits    | Password must contain a number |
      | NOLOWERCASE | Password must contain lowercase|