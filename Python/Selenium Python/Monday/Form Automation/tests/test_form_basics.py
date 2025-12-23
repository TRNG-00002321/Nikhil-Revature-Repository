"""
Basic form interaction tests.

Target: https://the-internet.herokuapp.com/login
"""

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import pytest


@pytest.fixture
def driver():
    service = Service(ChromeDriverManager().install())
    driver = webdriver.Chrome(service=service)
    driver.implicitly_wait(10)
    yield driver
    driver.quit()


class TestLoginForm:
    """Test the login form functionality."""

    def test_successful_login(self, driver):
        """
        Test successful login with valid credentials.
        """
        driver.get("https://the-internet.herokuapp.com/login")

        # Enter username
        driver.find_element(By.ID, "username").send_keys("tomsmith")
        # Enter password
        driver.find_element(By.ID, "password").send_keys("SuperSecretPassword!")
        # Click login
        driver.find_element(By.CSS_SELECTOR, "button[type='submit']").click()

        # Verify success message appears
        success_msg = WebDriverWait(driver, 10).until(
            EC.visibility_of_element_located((By.ID, "flash"))
        )
        assert "You logged into a secure area!" in success_msg.text

        # Verify URL contains "/secure"
        assert "/secure" in driver.current_url

    def test_failed_login_wrong_password(self, driver):
        """
        Test failed login with wrong password.
        """
        driver.get("https://the-internet.herokuapp.com/login")

        # Enter valid username, wrong password
        driver.find_element(By.ID, "username").send_keys("tomsmith")
        driver.find_element(By.ID, "password").send_keys("WrongPassword")
        driver.find_element(By.CSS_SELECTOR, "button[type='submit']").click()

        # Verify error message is displayed
        error_msg = WebDriverWait(driver, 10).until(
            EC.visibility_of_element_located((By.ID, "flash"))
        )
        assert "Your password is invalid!" in error_msg.text

    def test_clear_and_retype(self, driver):
        """
        Test clearing field and retyping.
        """
        driver.get("https://the-internet.herokuapp.com/login")

        username = driver.find_element(By.ID, "username")

        # Type, clear, retype
        username.send_keys("wrong_user")
        username.clear()
        username.send_keys("tomsmith")

        assert username.get_attribute("value") == "tomsmith"

    def test_logout_after_login(self, driver):
        """
        Test complete login and logout flow.
        """
        driver.get("https://the-internet.herokuapp.com/login")

        # Login
        driver.find_element(By.ID, "username").send_keys("tomsmith")
        driver.find_element(By.ID, "password").send_keys("SuperSecretPassword!")
        driver.find_element(By.CSS_SELECTOR, "button[type='submit']").click()

        # Wait for logout button
        logout_btn = WebDriverWait(driver, 10).until(
            EC.element_to_be_clickable((By.CSS_SELECTOR, "a.button"))
        )
        logout_btn.click()

        # Verify redirect to login page
        WebDriverWait(driver, 10).until(
            EC.url_contains("/login")
        )
        assert "/login" in driver.current_url

        # Verify logout success message
        flash_msg = driver.find_element(By.ID, "flash")
        assert "You logged out of the secure area!" in flash_msg.text
