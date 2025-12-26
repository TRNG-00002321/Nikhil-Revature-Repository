"""
Registration Step Definitions

Note: This exercise uses mock data since the-internet doesn't have
a registration form. Focus on learning the Behave patterns.
"""
from behave import given, when, then


# Mock registration state (in real tests, use actual page interactions)
class MockRegistration:
    def __init__(self):
        self.name = None
        self.email = None
        self.password = None
        self.confirm_password = None
        self.submitted = False
        self.error_message = None
        self.success = False

    def validate(self):
        """Simple validation logic for learning purposes."""
        if self.password != self.confirm_password:
            self.error_message = "Passwords do not match"
            return False
        if '@' not in self.email:
            self.error_message = "Invalid email format"
            return False
        if len(self.password) < 8:
            self.error_message = "Password must be 8+ characters"
            return False
        if not any(c.isdigit() for c in self.password):
            self.error_message = "Password must contain a number"
            return False
        if not any(c.islower() for c in self.password):
            self.error_message = "Password must contain lowercase"
            return False

        self.success = True
        return True


@given('I am on the registration page')
def step_on_registration_page(context):
    """Initialize registration context."""
    context.registration = MockRegistration()
    # In real tests: context.driver.get(registration_url)


@when('I enter my name "{name}"')
def step_enter_name(context, name):
    """Enter name in registration form."""
    context.registration.name = name
    # In real tests: find_element and send_keys


@when('I enter my email "{email}"')
def step_enter_email(context, email):
    """Enter email in registration form."""
    context.registration.email = email


@when('I enter my password "{password}"')
def step_enter_password(context, password):
    """Enter password in registration form."""
    context.registration.password = password


@when('I confirm my password "{password}"')
def step_confirm_password(context, password):
    """Enter password confirmation."""
    context.registration.confirm_password = password


@when('I click the register button')
def step_click_register(context):
    """Submit registration form."""
    context.registration.submitted = True
    context.registration.validate()


@then('I should see a success message')
def step_see_success(context):
    """Verify registration success."""
    assert context.registration.success, "Registration should be successful"


@then('I should receive a verification email')
def step_receive_email(context):
    """Verify email sent (mock check)."""
    # In real tests: check email service or verify sent status
    assert context.registration.success, "Email only sent on success"


@then('I should see an error "{error_message}"')
def step_see_error(context, error_message):
    """Verify error message displayed."""
    assert context.registration.error_message == error_message, \
        f"Expected '{error_message}' but got '{context.registration.error_message}'"


@then('I should see validation message "{message}"')
def step_see_validation(context, message):
    """Verify validation message."""
    assert context.registration.error_message == message, \
        f"Expected '{message}' but got '{context.registration.error_message}'"