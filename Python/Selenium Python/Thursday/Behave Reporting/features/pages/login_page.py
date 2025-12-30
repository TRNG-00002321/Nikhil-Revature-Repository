"""
Login Page Object.
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait

from pages.base_page import BasePage


class LoginPage(BasePage):
    """Page object for the login page."""

    # Locators
    USERNAME = (By.ID, "username")
    PASSWORD = (By.ID, "password")
    LOGIN_BUTTON = (By.CSS_SELECTOR, "button[type='submit']")
    FLASH_MESSAGE = (By.ID, "flash")
    LOGOUT_BUTTON = (By.CSS_SELECTOR, "a.button")

    def navigate_to_login(self):
        """Navigate to login page."""
        return self.navigate_to("/login")

    def enter_username(self, username):
        """Enter username."""
        self.type_text(self.USERNAME, username)
        return self

    def enter_password(self, password):
        """Enter password."""
        self.type_text(self.PASSWORD, password)
        return self

    def click_login(self):
        """Click login button."""
        self.click(self.LOGIN_BUTTON)
        return self

    def login(self, username, password):
        """Complete login process."""
        self.enter_username(username)
        self.enter_password(password)
        self.click_login()
        return self

    def get_flash_message(self):
        """Get flash message text."""
        return self.get_text(self.FLASH_MESSAGE)

    def is_login_successful(self):
        message = self.get_flash_message().lower()
        return "secure area" in message

    def click_logout(self):
        """Click logout button."""
        self.click(self.LOGOUT_BUTTON)
        return self