"""
Multi-browser driver factory with webdriver-manager.
Supports:
- Chrome
- Firefox
- Edge
"""
import os
from contextlib import contextmanager
from selenium import webdriver
from selenium.webdriver.chrome.service import Service as ChromeService
from selenium.webdriver.chrome.options import Options as ChromeOptions
from selenium.webdriver.firefox.service import Service as FirefoxService
from selenium.webdriver.firefox.options import Options as FirefoxOptions
from selenium.webdriver.edge.service import Service as EdgeService
from selenium.webdriver.edge.options import Options as EdgeOptions
from webdriver_manager.chrome import ChromeDriverManager
from webdriver_manager.firefox import GeckoDriverManager
from webdriver_manager.microsoft import EdgeChromiumDriverManager
import subprocess
import re

EDGE_DRIVER_PATH = r"C:\WebDrivers\msedgedriver.exe"

@contextmanager
def create_driver(browser: str, headless: bool = False):
    driver = None

    try:
        if browser == "chrome":
            options = ChromeOptions()
            if headless:
                options.add_argument("--headless=new")
            options.add_argument("--window-size=1920,1080")

            service = ChromeService(ChromeDriverManager().install())
            driver = webdriver.Chrome(service=service, options=options)

        elif browser == "edge":
            if not os.path.exists(EDGE_DRIVER_PATH):
                raise RuntimeError(
                    f"EdgeDriver not found at {EDGE_DRIVER_PATH}. "
                    "Download it from https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/"
                )

            options = EdgeOptions()
            if headless:
                options.add_argument("--headless=new")

            options.add_argument("--window-size=1920,1080")
            options.add_argument("--disable-gpu")
            options.add_argument("--no-sandbox")

            service = EdgeService(executable_path=EDGE_DRIVER_PATH)
            driver = webdriver.Edge(service=service, options=options)

        else:
            raise ValueError(f"Unsupported browser: {browser}")

        yield driver

    finally:
        if driver:
            driver.quit()

def get_browser_version(browser: str) -> str:
    """
    Get the installed browser version.

    Args:
        browser: Browser name ("chrome", "firefox", "edge")

    Returns:
        Version string or error message
    """
    try:
        if browser.lower() == "chrome":
            # Try different Chrome commands based on OS
            commands = [
                ["google-chrome", "--version"],
                ["google-chrome-stable", "--version"],
                ["chromium", "--version"],
                [r"C:\Program Files\Google\Chrome\Application\chrome.exe", "--version"],
                [r"C:\Program Files (x86)\Google\Chrome\Application\chrome.exe", "--version"],
            ]

        elif browser.lower() == "firefox":
            # Try different Firefox commands based on OS
            commands = [
                ["firefox", "--version"],
                ["firefox-esr", "--version"],
                [r"C:\Program Files\Mozilla Firefox\firefox.exe", "--version"],
                [r"C:\Program Files (x86)\Mozilla Firefox\firefox.exe", "--version"],
            ]

        elif browser.lower() == "edge":
            # Try different Edge commands based on OS
            commands = [
                ["microsoft-edge", "--version"],
                [r"C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe", "--version"],
                [r"C:\Program Files\Microsoft\Edge\Application\msedge.exe", "--version"],
            ]

        else:
            return f"Unsupported browser: {browser}"

        # Try each command until one works
        for cmd in commands:
            try:
                result = subprocess.run(
                    cmd,
                    capture_output=True,
                    text=True,
                    timeout=5
                )
                if result.returncode == 0:
                    # Extract version number from output
                    version_match = re.search(r'(\d+\.[\d.]+)', result.stdout)
                    if version_match:
                        return version_match.group(1)
                    return result.stdout.strip()
            except (FileNotFoundError, subprocess.TimeoutExpired):
                continue

        return f"{browser.capitalize()} not found on this system"

    except Exception as e:
        return f"Error detecting {browser} version: {str(e)}"