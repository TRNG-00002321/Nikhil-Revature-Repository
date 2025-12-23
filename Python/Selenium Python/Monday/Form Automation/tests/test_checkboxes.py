"""
Checkbox interaction tests.

Target: https://the-internet.herokuapp.com/checkboxes
"""

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import pytest


@pytest.fixture
def driver():
    service = Service(ChromeDriverManager().install())
    driver = webdriver.Chrome(service=service)
    driver.implicitly_wait(10)
    driver.get("https://the-internet.herokuapp.com/checkboxes")
    yield driver
    driver.quit()


class TestCheckboxes:
    """Test checkbox interactions."""

    def test_check_unchecked_box(self, driver):
        """
        Check a checkbox that starts unchecked.
        """
        checkboxes = driver.find_elements(By.CSS_SELECTOR, "input[type='checkbox']")
        checkbox1 = checkboxes[0]

        # Check if already checked
        if not checkbox1.is_selected():
            checkbox1.click()

        assert checkbox1.is_selected()

    def test_uncheck_checked_box(self, driver):
        """
        Uncheck a checkbox that starts checked.
        """
        checkboxes = driver.find_elements(By.CSS_SELECTOR, "input[type='checkbox']")
        checkbox2 = checkboxes[1]  # This one is checked by default

        # Uncheck the checkbox if it's checked
        if checkbox2.is_selected():
            checkbox2.click()

        assert not checkbox2.is_selected()

    def test_toggle_checkbox(self, driver):
        """
        Toggle checkbox state regardless of initial state.
        """
        checkboxes = driver.find_elements(By.CSS_SELECTOR, "input[type='checkbox']")
        checkbox = checkboxes[0]

        # Get initial state
        initial_state = checkbox.is_selected()

        # Toggle
        checkbox.click()

        # Verify state changed
        assert checkbox.is_selected() != initial_state

    def test_select_all_checkboxes(self, driver):
        """
        Ensure all checkboxes are checked.
        """
        checkboxes = driver.find_elements(By.CSS_SELECTOR, "input[type='checkbox']")

        for checkbox in checkboxes:
            if not checkbox.is_selected():
                checkbox.click()

        # Verify all are checked
        for checkbox in checkboxes:
            assert checkbox.is_selected(), "Not all checkboxes are checked"
