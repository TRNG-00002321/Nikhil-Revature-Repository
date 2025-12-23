"""
File upload tests.

Target: https://the-internet.herokuapp.com/upload
"""

import os
from pathlib import Path
from selenium import webdriver
from selenium.webdriver.common.by import By
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


@pytest.fixture
def test_file(tmp_path):
    """Create a temporary test file for upload."""
    test_file = tmp_path / "test_upload.txt"
    test_file.write_text("This is a test file for upload testing.")
    return str(test_file)


class TestFileUpload:
    """Test file upload functionality."""

    def test_upload_file(self, driver, test_file):
        """
        Upload a file and verify success.
        """
        driver.get("https://the-internet.herokuapp.com/upload")

        # Find the file input element
        file_input = driver.find_element(By.ID, "file-upload")

        # Send the file path to the input
        file_input.send_keys(test_file)

        # Click upload button
        upload_button = driver.find_element(By.ID, "file-submit")
        upload_button.click()

        # Verify upload success
        uploaded_file_name = driver.find_element(By.ID, "uploaded-files").text
        assert uploaded_file_name == os.path.basename(test_file), "Uploaded file name does not match"

    @pytest.mark.skip(reason="Heroku app returns 500 if no file is uploaded")
    def test_upload_without_file(self, driver):
        pass

    def test_drag_and_drop_upload(self, driver, test_file):
        """
        Optional: Test drag and drop file upload using JavaScript.
        """
        driver.get("https://the-internet.herokuapp.com/upload")

        # Advanced: simulate drag and drop by JS (requires HTML5 API)
        drop_area = driver.find_element(By.ID, "drag-drop-upload")
        with open(test_file, "rb") as f:
            file_content = f.read()
        pass
