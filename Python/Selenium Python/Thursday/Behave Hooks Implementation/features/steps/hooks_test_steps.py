"""Steps to verify hooks are working."""
from behave import given, when, then

@given('I verify hooks are working')
def step_verify_hooks(context):
    # Driver should be set up in before_all / before_scenario hook
    assert hasattr(context, 'driver'), "Driver should be initialized"
    print("       Hooks working: driver initialized")

@when('I perform an action')
def step_perform_action(context):
    context.driver.get("https://the-internet.herokuapp.com/")

@then('I should see the result')
def step_see_result(context):
    assert "The Internet" in context.driver.title


@given('the database is initialized')
def step_db_init(context):
    # Database hook (e.g., before_tag @database) should set this
    assert hasattr(context, 'db'), "Database should be initialized by hook"
    assert context.db["connected"] is True
    print("       Database hook executed")

@when('I query for data')
def step_query(context):
    # Simulate a query using data created by the hook
    context.query_results = context.db.get("data", [])

@then('I should get results')
def step_results(context):
    assert context.query_results, "Expected query results but got none"
    print("       Database query returned results")


@given('I am in headless mode')
def step_headless(context):
    # Headless hook (e.g., before_tag @headless) should set this flag
    assert hasattr(context, 'headless'), "Headless flag not set by hook"
    assert context.headless is True
    print("       Headless mode confirmed")

@when('I wait for slow operation')
def step_slow(context):
    import time
    time.sleep(1)  # Simulate slow operation

@then('I should complete successfully')
def step_complete(context):
    assert True
