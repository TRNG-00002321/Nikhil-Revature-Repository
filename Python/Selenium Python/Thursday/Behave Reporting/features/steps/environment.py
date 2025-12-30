"""
Enhanced environment with Allure reporting.
"""
import allure
from datetime import datetime


def after_scenario(context, scenario):
    """Attach screenshot and logs on failure."""
    if scenario.status == 'failed':
        # Capture screenshot
        if hasattr(context, 'driver'):
            screenshot = context.driver.get_screenshot_as_png()
            allure.attach(
                screenshot,
                name=f'Failure_{scenario.name}',
                attachment_type=allure.attachment_type.PNG
            )

        # Attach browser logs
        if hasattr(context, 'driver'):
            try:
                logs = context.driver.get_log('browser')
                if logs:
                    log_text = '\n'.join([str(l) for l in logs])
                    allure.attach(
                        log_text,
                        name='Browser Logs',
                        attachment_type=allure.attachment_type.TEXT
                    )
            except Exception:
                pass

    # Cleanup
    if hasattr(context, 'driver'):
        context.driver.quit()


def before_feature(context, feature):
    """Add feature to Allure."""
    # Feature-level setup for reporting
    allure.dynamic.feature(feature.name)


def before_scenario(context, scenario):
    """Add scenario details to Allure."""
    allure.dynamic.story(scenario.name)

    # Add tags
    for tag in scenario.effective_tags:
        allure.dynamic.tag(tag)