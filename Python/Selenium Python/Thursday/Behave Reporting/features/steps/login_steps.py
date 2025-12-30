"""
Steps with Allure enhancements.
"""
from behave import given, when, then
import allure

@given('the user is on the login page')
@allure.step('Navigate to login page')
def step_on_login_page(context):
    context.login_page.navigate_to_login()


@when('the user logs in with "{username}" and "{password}"')
@allure.step('Login with credentials')
def step_login(context, username, password):
    with allure.step(f'Enter username: {username}'):
        context.login_page.enter_username(username)

    with allure.step('Enter password'):
        context.login_page.enter_password(password)

    with allure.step('Click login button'):
        context.login_page.click_login()


@then('the login should be successful')
@allure.step('Verify successful login')
def step_verify_success(context):
    assert context.login_page.is_login_successful()

    # Attach screenshot as evidence
    screenshot = context.driver.get_screenshot_as_png()
    allure.attach(screenshot, name='Login Success',
                  attachment_type=allure.attachment_type.PNG)


@then('the login should fail')
@allure.step('Verify failed login')
def step_verify_failure(context):
    assert context.login_page.is_login_failed(), "Login should have failed"

    # Attach screenshot as evidence
    screenshot = context.driver.get_screenshot_as_png()
    allure.attach(screenshot, name='Login Failure',
                  attachment_type=allure.attachment_type.PNG)