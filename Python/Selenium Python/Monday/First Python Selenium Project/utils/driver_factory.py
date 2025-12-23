"""
Driver factory for Selenium WebDriver with automatic driver management.
"""
from contextlib import contextmanager
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from webdriver_manager.chrome import ChromeDriverManager


@contextmanager
def create_chrome_driver(headless: bool = False):
    """
    Context manager for creating and managing Chrome WebDriver.

    Args:
        headless: If True, runs Chrome in headless mode (no GUI)

    Yields:
        webdriver.Chrome: Configured Chrome WebDriver instance

    Example:
        with create_chrome_driver(headless=True) as driver:
            driver.get("https://example.com")
            # Do your automation tasks
        # Driver automatically quits when exiting the context
    """
    driver = None
    try:
        # 1. Create ChromeOptions and configure headless if needed
        options = Options()
        if headless:
            options.add_argument("--headless")
            options.add_argument("--no-sandbox")
            options.add_argument("--disable-dev-shm-usage")

        # Optional: Add other useful options
        options.add_argument("--disable-blink-features=AutomationControlled")
        options.add_argument("--start-maximized")

        # 2. Set up Service with ChromeDriverManager
        service = Service(ChromeDriverManager().install())

        # 3. Create driver and yield it
        driver = webdriver.Chrome(service=service, options=options)
        yield driver

    finally:
        # Ensure quit() is called to clean up resources
        if driver:
            driver.quit()


if __name__ == "__main__":
    # Quick test of the driver factory
    print("Testing driver factory...")
    with create_chrome_driver(headless=False) as driver:
        driver.get("https://www.google.com")
        print(f"Page title: {driver.title}")
        print("Driver factory working correctly!")