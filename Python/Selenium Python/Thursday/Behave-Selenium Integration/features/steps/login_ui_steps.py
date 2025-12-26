"""
Login UI Step Definitions using Page Objects.
"""
from behave import given, when, then
from pages.login_page import LoginPage


def get_login_page(context):
    """Get or create LoginPage instance."""
    if not hasattr(context, 'login_page'):
        context.login_page = LoginPage(context.driver)
    return context.login_page


@given('the browser is on the login page')
def step_on_login_page(context):
    """Navigate to login page."""
    get_login_page(context).navigate_to_login()


@given('I am logged in as "{username}"')
def step_logged_in_as(context, username):
    """Log in with default password."""
    page = get_login_page(context)
    page.navigate_to_login()
    page.login(username, "SuperSecretPassword!")
    assert page.is_login_successful(), "Login should succeed"


@when('I enter username "{username}"')
def step_enter_username(context, username):
    """Enter username in field."""
    get_login_page(context).enter_username(username)


@when('I enter password "{password}"')
def step_enter_password(context, password):
    """Enter password in field."""
    get_login_page(context).enter_password(password)


@when('I click the login button')
def step_click_login(context):
    """Click login button."""
    get_login_page(context).click_login()


@when('I click logout')
def step_click_logout(context):
    """Click logout button."""
    get_login_page(context).click_logout()


@then('I should be on the secure area page')
def step_on_secure_area(context):
    """Verify on secure page."""
    page = get_login_page(context)
    assert page.is_login_successful(), "Should be on secure area"


@then('I should remain on the login page')
def step_remain_on_login(context):
    """Verify still on login page."""
    page = get_login_page(context)
    assert not page.is_login_successful(), "Should remain on login page"


@then('I should be on the login page')
def step_on_login_page_verify(context):
    """Verify on login page."""
    page = get_login_page(context)
    assert "/login" in page.get_current_url(), "Should be on login page"


@then('I should see message containing "{text}"')
def step_see_message(context, text):
    """Verify message displayed."""
    message = get_login_page(context).get_flash_message()
    assert text in message, f"Expected '{text}' in '{message}'"


@then('I should see error containing "{text}"')
def step_see_error(context, text):
    """Verify error displayed."""
    message = get_login_page(context).get_flash_message()
    assert text in message, f"Expected '{text}' in '{message}'"