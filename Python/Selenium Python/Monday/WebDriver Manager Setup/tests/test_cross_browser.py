"""
Cross-browser compatibility tests.
Run the same tests across Chrome, Firefox, and Edge.
"""
import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import sys
import os
from pathlib import Path

sys.path.insert(0, '..')
from utils.multi_browser_factory import create_driver

# Parameterize tests to run on multiple browsers
BROWSERS = ["chrome", "edge"]


@pytest.mark.parametrize("browser", BROWSERS)
def test_page_loads_correctly(browser):
    """
    Verify the page loads correctly in each browser.

    Steps:
    1. Navigate to the-internet homepage
    2. Verify page title
    3. Verify heading text
    """
    with create_driver(browser, headless=True) as driver:
        driver.get("https://the-internet.herokuapp.com/")

        assert "The Internet" in driver.title

        heading = driver.find_element(By.TAG_NAME, "h1")
        assert "Welcome to the-internet" in heading.text


@pytest.mark.parametrize("browser", BROWSERS)
def test_form_interaction(browser):
    """
    Verify form interaction works in each browser.

    Steps:
    1. Navigate to login page
    2. Enter credentials
    3. Submit form
    4. Verify result
    """
    with create_driver(browser, headless=True) as driver:
        # Navigate to login page
        driver.get("https://the-internet.herokuapp.com/login")

        # Wait for page to load
        wait = WebDriverWait(driver, 10)
        username_field = wait.until(
            EC.presence_of_element_located((By.ID, "username"))
        )

        # Enter valid credentials (from the-internet.herokuapp.com)
        username_field.send_keys("tomsmith")

        password_field = driver.find_element(By.ID, "password")
        password_field.send_keys("SuperSecretPassword!")

        # Submit form
        login_button = driver.find_element(By.CSS_SELECTOR, "button[type='submit']")
        login_button.click()

        # Verify successful login
        wait.until(EC.url_contains("/secure"))

        # Verify success message
        flash_message = driver.find_element(By.ID, "flash")
        assert "You logged into a secure area!" in flash_message.text

        # Verify we're on the secure page
        assert "/secure" in driver.current_url

        # Verify logout button is present
        logout_button = driver.find_element(By.CSS_SELECTOR, "a.button")
        assert logout_button.is_displayed()


@pytest.mark.parametrize("browser", BROWSERS)
def test_screenshot_capture(browser):
    """
    Verify screenshot capture works in each browser.

    Steps:
    1. Navigate to a page
    2. Take screenshot
    3. Verify file was created
    """
    # Create screenshots directory if it doesn't exist
    screenshots_dir = Path("screenshots")
    screenshots_dir.mkdir(exist_ok=True)

    screenshot_path = screenshots_dir / f"{browser}_screenshot.png"

    # Remove old screenshot if it exists
    if screenshot_path.exists():
        screenshot_path.unlink()

    with create_driver(browser, headless=True) as driver:
        # Navigate to a page
        driver.get("https://the-internet.herokuapp.com/")

        # Wait for page to fully load
        wait = WebDriverWait(driver, 10)
        wait.until(EC.presence_of_element_located((By.TAG_NAME, "h1")))

        # Take screenshot
        success = driver.save_screenshot(str(screenshot_path))
        assert success, f"Failed to save screenshot for {browser}"

    # Verify file was created and has content
    assert screenshot_path.exists(), f"Screenshot file not found: {screenshot_path}"
    assert screenshot_path.stat().st_size > 0, f"Screenshot file is empty: {screenshot_path}"

    print(f"Screenshot saved: {screenshot_path.absolute()}")


@pytest.mark.parametrize("browser", BROWSERS)
def test_dropdown_interaction(browser):
    """
    Verify dropdown interaction works across browsers.

    Steps:
    1. Navigate to dropdown page
    2. Select an option
    3. Verify selection
    """
    with create_driver(browser, headless=True) as driver:
        driver.get("https://the-internet.herokuapp.com/dropdown")

        # Find dropdown element
        from selenium.webdriver.support.select import Select
        dropdown = Select(driver.find_element(By.ID, "dropdown"))

        # Verify default selection
        assert dropdown.first_selected_option.text == "Please select an option"

        # Select by visible text
        dropdown.select_by_visible_text("Option 1")
        assert dropdown.first_selected_option.text == "Option 1"

        # Select by value
        dropdown.select_by_value("2")
        assert dropdown.first_selected_option.text == "Option 2"


@pytest.mark.parametrize("browser", BROWSERS)
def test_checkbox_interaction(browser):
    """
    Verify checkbox interaction works across browsers.

    Steps:
    1. Navigate to checkboxes page
    2. Toggle checkboxes
    3. Verify states
    """
    with create_driver(browser, headless=True) as driver:
        driver.get("https://the-internet.herokuapp.com/checkboxes")

        # Find all checkboxes
        checkboxes = driver.find_elements(By.CSS_SELECTOR, "input[type='checkbox']")
        assert len(checkboxes) == 2

        # First checkbox should be unchecked, second should be checked
        assert not checkboxes[0].is_selected()
        assert checkboxes[1].is_selected()

        # Click first checkbox to check it
        checkboxes[0].click()
        assert checkboxes[0].is_selected()

        # Click second checkbox to uncheck it
        checkboxes[1].click()
        assert not checkboxes[1].is_selected()


@pytest.mark.parametrize("browser", BROWSERS)
def test_dynamic_content(browser):
    """
    Verify dynamic content loading works across browsers.

    Steps:
    1. Navigate to dynamic loading page
    2. Click start button
    3. Wait for content to load
    4. Verify content appears
    """
    with create_driver(browser, headless=True) as driver:
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1")

        # Click the start button
        start_button = driver.find_element(By.CSS_SELECTOR, "#start button")
        start_button.click()

        # Wait for the loading bar to disappear and content to appear
        wait = WebDriverWait(driver, 10)
        finish_text = wait.until(
            EC.visibility_of_element_located((By.ID, "finish"))
        )

        # Verify the content
        assert "Hello World!" in finish_text.text


if __name__ == "__main__":
    # Run tests with pytest
    pytest.main([__file__, "-v", "--tb=short"])