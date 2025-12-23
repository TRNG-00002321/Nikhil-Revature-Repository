"""
Dropdown interaction tests.

Target: https://the-internet.herokuapp.com/dropdown
"""

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import Select
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import pytest


@pytest.fixture
def driver():
    service = Service(ChromeDriverManager().install())
    driver = webdriver.Chrome(service=service)
    driver.implicitly_wait(10)
    driver.get("https://the-internet.herokuapp.com/dropdown")
    yield driver
    driver.quit()


class TestDropdowns:
    """Test dropdown interactions using Select class."""

    def test_select_by_visible_text(self, driver):
        """
        Select option by its visible text.
        """
        dropdown = driver.find_element(By.ID, "dropdown")
        select = Select(dropdown)

        # Select "Option 1" by visible text
        select.select_by_visible_text("Option 1")

        # Verify selection
        selected = select.first_selected_option
        assert selected.text == "Option 1"

    def test_select_by_value(self, driver):
        """
        Select option by its value attribute.
        """
        dropdown = driver.find_element(By.ID, "dropdown")
        select = Select(dropdown)

        # Select Option 2 by value
        select.select_by_value("2")

        # Verify selection
        selected = select.first_selected_option
        assert selected.text == "Option 2"

    def test_select_by_index(self, driver):
        """
        Select option by its index (0-based).
        """
        dropdown = driver.find_element(By.ID, "dropdown")
        select = Select(dropdown)

        # Select Option 1 by index (index 1)
        select.select_by_index(1)

        # Verify selection
        selected = select.first_selected_option
        assert selected.text == "Option 1"

    def test_get_all_options(self, driver):
        """
        Get all available options from dropdown.
        """
        dropdown = driver.find_element(By.ID, "dropdown")
        select = Select(dropdown)

        # Get all options
        all_options = select.options
        option_texts = [opt.text for opt in all_options]

        # Verify expected options are present
        expected_options = ["Please select an option", "Option 1", "Option 2"]
        assert option_texts == expected_options

    def test_dropdown_without_select_class(self, driver):
        driver.get("https://the-internet.herokuapp.com/jqueryui/menu")

        wait = WebDriverWait(driver, 10)

        # Find top-level menu by text
        enabled_menu = wait.until(EC.visibility_of_element_located((By.LINK_TEXT, "Enabled")))
        downloads_menu = wait.until(EC.visibility_of_element_located((By.LINK_TEXT, "Downloads")))
        pdf_option = wait.until(EC.visibility_of_element_located((By.LINK_TEXT, "PDF")))

        actions = ActionChains(driver)
        actions.move_to_element(enabled_menu).perform()  # Hover over Enabled
        actions.move_to_element(downloads_menu).perform()  # Hover over Downloads
        pdf_option.click()  # Click PDF
