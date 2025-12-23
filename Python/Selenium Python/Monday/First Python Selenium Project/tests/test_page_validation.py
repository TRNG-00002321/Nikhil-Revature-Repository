"""
Test page content validation using Python Selenium.
Implement tests that:
1. Validate page title
2. Check for specific text content
3. Verify element presence
4. Check element attributes
"""
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import sys

sys.path.insert(0, '..')
from utils.driver_factory import create_chrome_driver


def test_page_title():
    """Verify the page title matches expected value."""
    with create_chrome_driver(headless=False) as driver:
        # Navigate to the page
        driver.get("https://the-internet.herokuapp.com/")

        # Wait for page to load
        wait = WebDriverWait(driver, 10)
        wait.until(EC.presence_of_element_located((By.TAG_NAME, "body")))

        # Get and verify page title
        actual_title = driver.title
        expected_title = "The Internet"

        assert actual_title == expected_title, \
            f"Expected title '{expected_title}', but got '{actual_title}'"

        print(f"Page title verified: '{actual_title}'")
        print("\nTest passed: test_page_title")


def test_heading_text():
    """Verify the main heading contains expected text."""
    with create_chrome_driver(headless=False) as driver:
        # Navigate to the page
        driver.get("https://the-internet.herokuapp.com/")

        # Wait for and find the main heading
        wait = WebDriverWait(driver, 10)
        heading = wait.until(
            EC.presence_of_element_located((By.CSS_SELECTOR, "h1.heading"))
        )

        # Verify heading text
        actual_text = heading.text
        expected_text = "Welcome to the-internet"

        assert expected_text in actual_text, \
            f"Expected '{expected_text}' in heading, but got '{actual_text}'"

        print(f"Main heading verified: '{actual_text}'")

        # Also verify the subheading
        subheading = driver.find_element(By.CSS_SELECTOR, "h2")
        print(f"Subheading found: '{subheading.text}'")

        print("\nTest passed: test_heading_text")


def test_links_present():
    """Verify that all example links are present on the page."""
    with create_chrome_driver(headless=False) as driver:
        # Navigate to the page
        driver.get("https://the-internet.herokuapp.com/")

        # Wait for content to load
        wait = WebDriverWait(driver, 10)
        wait.until(EC.presence_of_element_located((By.TAG_NAME, "ul")))

        # Find all links in the examples list
        links = driver.find_elements(By.CSS_SELECTOR, "ul li a")

        # Extract link texts using list comprehension
        link_texts = [link.text for link in links]

        # Verify we have links
        assert len(link_texts) > 0, "No links found on the page"
        print(f"Found {len(link_texts)} example links on the page")

        # Check for some expected links
        expected_links = [
            "A/B Testing",
            "Add/Remove Elements",
            "Form Authentication",
            "Checkboxes",
            "Dropdown"
        ]

        for expected_link in expected_links:
            assert expected_link in link_texts, \
                f"Expected link '{expected_link}' not found in page"
            print(f"  '{expected_link}' is present")

        # Display all links found
        print(f"\nAll links found ({len(link_texts)}):")
        for i, text in enumerate(link_texts[:10], 1):  # Show first 10
            print(f"  {i}. {text}")
        if len(link_texts) > 10:
            print(f"  ... and {len(link_texts) - 10} more")

        print("\nTest passed: test_links_present")


def test_link_attributes():
    """Verify that links have correct href attributes."""
    with create_chrome_driver(headless=False) as driver:
        # Navigate to the page
        driver.get("https://the-internet.herokuapp.com/")

        # Wait for content to load
        wait = WebDriverWait(driver, 10)
        wait.until(EC.presence_of_element_located((By.TAG_NAME, "ul")))

        # Find all links
        links = driver.find_elements(By.CSS_SELECTOR, "ul li a")

        # Test specific links and their href attributes
        test_cases = {
            "A/B Testing": "/abtest",
            "Add/Remove Elements": "/add_remove_elements/",
            "Form Authentication": "/login",
            "Checkboxes": "/checkboxes",
            "Dropdown": "/dropdown"
        }

        for link in links:
            link_text = link.text
            href = link.get_attribute("href")

            # Verify href attribute exists
            assert href is not None, f"Link '{link_text}' has no href attribute"

            # Check if this is one of our test cases
            if link_text in test_cases:
                expected_path = test_cases[link_text]
                assert expected_path in href, \
                    f"Expected '{expected_path}' in href for '{link_text}', got '{href}'"
                print(f"'{link_text}' -> {href}")

        # Additional attribute checks
        print("\nAdditional attribute validations:")

        # Check that all links have valid URLs
        for link in links[:5]:  # Check first 5 links
            href = link.get_attribute("href")
            assert href.startswith("http"), \
                f"Link href should be absolute URL: {href}"

            # Check other attributes
            target = link.get_attribute("target")
            class_name = link.get_attribute("class")

            print(f"  Link: {link.text[:30]}")
            print(f"    href: {href}")
            print(f"    target: {target if target else 'None'}")
            print(f"    class: {class_name if class_name else 'None'}")

        print("\nTest passed: test_link_attributes")


if __name__ == "__main__":
    print("=" * 60)
    print("Running Selenium Content Validation Tests")
    print("=" * 60)

    try:
        print("\n[Test 1/4] Page Title Validation")
        print("-" * 60)
        test_page_title()

        print("\n[Test 2/4] Heading Text Validation")
        print("-" * 60)
        test_heading_text()

        print("\n[Test 3/4] Links Presence Validation")
        print("-" * 60)
        test_links_present()

        print("\n[Test 4/4] Link Attributes Validation")
        print("-" * 60)
        test_link_attributes()

        print("\n" + "=" * 60)
        print("ALL TESTS PASSED!")
        print("=" * 60)

    except Exception as e:
        print(f"\nTest failed with error: {e}")
        import traceback

        traceback.print_exc()