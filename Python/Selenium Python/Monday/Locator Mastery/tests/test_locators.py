"""
Locator Strategy Mastery Tests – Robust Version
"""

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait
from webdriver_manager.chrome import ChromeDriverManager
import pytest
import logging

logging.basicConfig(level=logging.INFO)

@pytest.fixture
def driver():
    """Create a fresh Chrome driver for each test."""
    service = Service(ChromeDriverManager().install())
    driver = webdriver.Chrome(service=service)
    driver.maximize_window()
    yield driver
    driver.quit()


def wait_for(driver, by, locator, timeout=10):
    """Helper function to wait for visibility of an element."""
    return WebDriverWait(driver, timeout).until(
        EC.visibility_of_element_located((by, locator))
    )


def wait_for_all(driver, by, locator, timeout=10):
    """Helper function to wait for presence of all elements."""
    return WebDriverWait(driver, timeout).until(
        EC.presence_of_all_elements_located((by, locator))
    )


class TestLocatorStrategies:
    """Test all 8 locator strategies on the login page."""

    @pytest.fixture(autouse=True)
    def setup(self, driver):
        driver.get("https://the-internet.herokuapp.com/login")
        self.driver = driver

    def test_locate_by_id(self):
        username = wait_for(self.driver, By.ID, "username")
        password = wait_for(self.driver, By.ID, "password")
        assert username.is_displayed(), "Username input not visible"
        assert password.is_displayed(), "Password input not visible"

    def test_locate_by_name(self):
        username = wait_for(self.driver, By.NAME, "username")
        password = wait_for(self.driver, By.NAME, "password")
        assert username.is_displayed()
        assert password.is_displayed()

    def test_locate_by_class_name(self):
        login_button = wait_for(self.driver, By.CLASS_NAME, "radius")
        assert login_button.is_displayed()

    def test_locate_by_tag_name(self):
        inputs = wait_for_all(self.driver, By.TAG_NAME, "input")
        assert len(inputs) >= 2, "Expected at least 2 input fields"

    def test_locate_by_link_text(self):
        link = wait_for(self.driver, By.LINK_TEXT, "Elemental Selenium")
        assert link.is_displayed()

    def test_locate_by_partial_link_text(self):
        link = wait_for(self.driver, By.PARTIAL_LINK_TEXT, "Elemental")
        assert link.is_displayed()

    def test_locate_by_css_selector(self):
        username = wait_for(self.driver, By.CSS_SELECTOR, "#username")
        password = wait_for(self.driver, By.CSS_SELECTOR, "input[type='password']")
        button = wait_for(self.driver, By.CSS_SELECTOR, ".radius")
        inputs_in_form = wait_for_all(self.driver, By.CSS_SELECTOR, "form input")
        assert username.is_displayed()
        assert password.is_displayed()
        assert button.is_displayed()
        assert len(inputs_in_form) >= 2

    def test_locate_by_xpath(self):
        username = wait_for(self.driver, By.XPATH, "//input[@id='username']")
        password = wait_for(self.driver, By.XPATH, "//input[@id='password']")
        heading = wait_for(self.driver, By.XPATH, "//h2[text()='Login Page']")
        link = wait_for(self.driver, By.XPATH, "//a[contains(text(), 'Elemental')]")
        form_parent = wait_for(self.driver, By.XPATH, "//input[@id='username']/ancestor::form")
        assert username.is_displayed()
        assert password.is_displayed()
        assert heading.is_displayed()
        assert link.is_displayed()
        assert form_parent.tag_name == "form"


class TestXPathAdvanced:
    """Advanced XPath exercises."""

    @pytest.fixture(autouse=True)
    def setup(self, driver):
        driver.get("https://the-internet.herokuapp.com/login")
        self.driver = driver

    def test_xpath_contains(self):
        login_button = wait_for(self.driver, By.XPATH, "//button[contains(text(), 'Login')]")
        assert login_button.is_displayed()

    def test_xpath_starts_with(self):
        username = wait_for(self.driver, By.XPATH, "//input[starts-with(@id, 'user')]")
        assert username.is_displayed()

    def test_xpath_text_functions(self):
        heading = wait_for(self.driver, By.XPATH, "//h2[text()='Login Page']")
        link = wait_for(self.driver, By.XPATH, "//a[normalize-space(text())='Elemental Selenium']")
        assert heading.is_displayed()
        assert link.is_displayed()

    def test_xpath_axes(self):
        password = wait_for(self.driver, By.XPATH, "//input[@id='username']/following::input[@type='password']")
        username = wait_for(self.driver, By.XPATH, "//input[@id='password']/preceding-sibling::input")
        form_parent = wait_for(self.driver, By.XPATH, "//input[@id='username']/parent::form")
        form_ancestor = wait_for(self.driver, By.XPATH, "//input[@id='username']/ancestor::form")

        assert password.is_displayed()
        assert username.is_displayed()
        assert form_parent.tag_name == "form"
        assert form_ancestor.tag_name == "form"


class TestChallengingDOM:
    """Handle elements with dynamic or unstable attributes."""

    @pytest.fixture(autouse=True)
    def setup(self, driver):
        self.driver = driver
        driver.get("https://the-internet.herokuapp.com/challenging_dom")

    def test_locate_by_relative_position(self):
        buttons = wait_for_all(self.driver, By.CLASS_NAME, "button")
        assert len(buttons) == 3
        logging.info(f"Button texts: {[btn.text for btn in buttons]}")

    def test_locate_table_cells(self):
        rows = wait_for_all(self.driver, By.XPATH, "//table/tbody/tr")
        first_cells = [row.find_element(By.XPATH, "./td[1]").text for row in rows]
        assert len(first_cells) == len(rows)
        logging.info(f"First column values: {first_cells}")

    def test_locate_by_text_content(self):
        edit_links = wait_for_all(self.driver, By.XPATH, "//a[text()='edit']")
        delete_links = wait_for_all(self.driver, By.XPATH, "//a[text()='delete']")
        assert len(edit_links) > 0
        assert len(delete_links) > 0
        logging.info(f"Number of delete links: {len(delete_links)}")
