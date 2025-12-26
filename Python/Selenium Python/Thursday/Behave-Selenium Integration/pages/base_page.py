"""
Base Page Object for Behave-Selenium integration.
"""
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException, NoSuchElementException


class BasePage:
    """Base class for all page objects."""

    BASE_URL = "https://the-internet.herokuapp.com"

    def __init__(self, driver):
        self.driver = driver
        self.wait = WebDriverWait(driver, 10)

    def navigate_to(self, path):
        """Navigate to a path relative to base URL."""
        url = f"{self.BASE_URL}{path}"
        self.driver.get(url)
        return self

    def wait_for_element(self, locator):
        """Wait for element to be visible and return it."""
        return self.wait.until(EC.visibility_of_element_located(locator))

    def wait_for_clickable(self, locator):
        """Wait for element to be clickable."""
        return self.wait.until(EC.element_to_be_clickable(locator))

    def wait_for_url_contains(self, text):
        """Wait for URL to contain specific text."""
        self.wait.until(EC.url_contains(text))

    def click(self, locator):
        """Click element after waiting for it."""
        self.wait_for_clickable(locator).click()

    def type_text(self, locator, text):
        """Type text into element after clearing."""
        element = self.wait_for_element(locator)
        element.clear()
        element.send_keys(text)

    def get_text(self, locator):
        """Get text from element."""
        return self.wait_for_element(locator).text

    def is_displayed(self, locator):
        """Check if element is displayed."""
        try:
            return self.driver.find_element(*locator).is_displayed()
        except NoSuchElementException:
            return False

    def get_current_url(self):
        """Get current page URL."""
        return self.driver.current_url

    def get_title(self):
        """Get page title."""
        return self.driver.title