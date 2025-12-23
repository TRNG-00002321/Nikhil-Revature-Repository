"""
Test navigation functionality using Python Selenium.
Implement tests that:
1. Navigate to https://the-internet.herokuapp.com/
2. Click on "Form Authentication" link
3. Verify URL changed to /login
4. Use back/forward navigation
5. Capture screenshots at key points
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import sys
import os

sys.path.insert(0, '..')
from utils.driver_factory import create_chrome_driver


def test_navigate_to_login_page():
    """
    Test: Navigate from home to login page

    Steps:
    1. Go to the-internet homepage
    2. Find and click "Form Authentication" link
    3. Assert URL contains "/login"
    4. Assert page contains "Login Page" heading
    """
    with create_chrome_driver(headless=False) as driver:
        # 1. Navigate to homepage
        driver.get("https://the-internet.herokuapp.com/")
        print(f"Homepage loaded: {driver.title}")

        # 2. Find and click "Form Authentication" link
        wait = WebDriverWait(driver, 10)
        form_auth_link = wait.until(
            EC.element_to_be_clickable((By.LINK_TEXT, "Form Authentication"))
        )
        form_auth_link.click()
        print("Clicked 'Form Authentication' link")

        # 3. Assert URL contains "/login"
        wait.until(EC.url_contains("/login"))
        current_url = driver.current_url
        assert "/login" in current_url, f"Expected '/login' in URL, got: {current_url}"
        print(f" URL changed to: {current_url}")

        # 4. Assert page contains "Login Page" heading
        heading = wait.until(
            EC.presence_of_element_located((By.TAG_NAME, "h2"))
        )
        assert "Login Page" in heading.text, f"Expected 'Login Page', got: {heading.text}"
        print(f"✓ Page heading verified: {heading.text}")

        print("\n Test passed: test_navigate_to_login_page")


def test_back_forward_navigation():
    """
    Test: Browser navigation (back/forward)

    Steps:
    1. Navigate to homepage
    2. Click a link to go to another page
    3. Use driver.back() to return
    4. Assert you're on homepage
    5. Use driver.forward() to go forward
    6. Assert you're on the second page again
    """
    with create_chrome_driver(headless=False) as driver:
        # 1. Navigate to homepage
        base_url = "https://the-internet.herokuapp.com/"
        driver.get(base_url)
        print(f"Started at: {driver.current_url}")

        # 2. Click a link to go to another page
        wait = WebDriverWait(driver, 10)
        form_auth_link = wait.until(
            EC.element_to_be_clickable((By.LINK_TEXT, "Form Authentication"))
        )
        form_auth_link.click()

        # Wait for navigation to complete
        wait.until(EC.url_contains("/login"))
        login_url = driver.current_url
        print(f"Navigated to: {login_url}")

        # 3. Use driver.back() to return
        driver.back()
        wait.until(lambda d: d.current_url == base_url)
        print(f"After back(): {driver.current_url}")

        # 4. Assert you're on homepage
        assert driver.current_url == base_url, f"Expected {base_url}, got: {driver.current_url}"
        print("✓ Successfully returned to homepage")

        # 5. Use driver.forward() to go forward
        driver.forward()
        wait.until(EC.url_contains("/login"))
        print(f"After forward(): {driver.current_url}")

        # 6. Assert you're on the second page again
        assert "/login" in driver.current_url, f"Expected '/login' in URL, got: {driver.current_url}"
        print("✓ Successfully moved forward to login page")

        print("\n Test passed: test_back_forward_navigation")


def test_capture_screenshot():
    """
    Test: Screenshot capture

    Steps:
    1. Navigate to any page
    2. Take a full page screenshot
    3. Save it to screenshots/homepage.png
    """
    with create_chrome_driver(headless=False) as driver:
        # 1. Navigate to a page
        driver.get("https://the-internet.herokuapp.com/")
        print(f"Navigated to: {driver.current_url}")

        # Wait for page to fully load
        wait = WebDriverWait(driver, 10)
        wait.until(EC.presence_of_element_located((By.TAG_NAME, "h1")))

        # 2 & 3. Take a screenshot and save it
        # Create screenshots directory if it doesn't exist
        os.makedirs("screenshots", exist_ok=True)

        screenshot_path = "screenshots/homepage.png"
        driver.save_screenshot(screenshot_path)

        # Verify file was created
        assert os.path.exists(screenshot_path), f"Screenshot not found at {screenshot_path}"
        file_size = os.path.getsize(screenshot_path)
        print(f"✓ Screenshot saved to: {screenshot_path} (Size: {file_size} bytes)")

        print("\nTest passed: test_capture_screenshot")


if __name__ == "__main__":
    print("=" * 60)
    print("Running Selenium Navigation Tests")
    print("=" * 60)

    try:
        print("\n[Test 1/3] Navigate to Login Page")
        print("-" * 60)
        test_navigate_to_login_page()

        print("\n[Test 2/3] Back/Forward Navigation")
        print("-" * 60)
        test_back_forward_navigation()

        print("\n[Test 3/3] Capture Screenshot")
        print("-" * 60)
        test_capture_screenshot()

        print("\n" + "=" * 60)
        print("ALL TESTS PASSED!")
        print("=" * 60)

    except Exception as e:
        print(f"\nTest failed with error: {e}")
        import traceback

        traceback.print_exc()