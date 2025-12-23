"""
Browser capability reporter.

Displays information about installed browsers and their capabilities.
"""

from utils.multi_browser_factory import create_driver


def generate_browser_report():
    """Generate a report of browser capabilities."""
    browsers = ["chrome", "firefox", "edge"]

    print("=" * 60)
    print("BROWSER CAPABILITY REPORT")
    print("=" * 60)

    for browser in browsers:
        print(f"\n{browser.upper()}")
        print("-" * 40)

        try:
            with create_driver(browser, headless=True) as driver:
                # TODO: Print browser capabilities
                # - driver.capabilities.get('browserName')
                # - driver.capabilities.get('browserVersion')
                # - driver.capabilities.get('platformName')
                print(f"  Browser Name   : {driver.capabilities.get('browserName')}")
                print(f"  Browser Version: {driver.capabilities.get('browserVersion')}")
                print(f"  Platform       : {driver.capabilities.get('platformName')}")
                pass

        except Exception as e:
            print(f" Not available: {e}")


if __name__ == "__main__":
    generate_browser_report()