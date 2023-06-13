#!/usr/bin/python3
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
import json

import sys
import unittest # Sort of abusing unittest library here, but it does the trick anyway


class UserInterfaceTest(unittest.TestCase):
    target_url = "http://127.0.0.1:8080"
    comparator_url = ""
    driver = None
    
    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome(service=Service("./chromedriver", 9515))
        cls.driver.implicitly_wait(10)
    
    @classmethod
    def tearDownClass(cls):
        cls.addClassCleanup(cls.driver.close)

    def setUp(self):
        self.driver.get(self.target_url)
        # Get the current URL for comparison, as the browser may have changed details of the provided URL
        self.comparator_url = self.driver.current_url

    def test_websiteTitle_isAsExpected(self):
        self.assertEqual(self.driver.title, "SQS Demo Application - User Interface")
        self.assertEqual(self.driver.current_url, self.comparator_url)

    def test_addInformationForm_isFunctional(self):
        confirm_button = self.driver.find_element(by = By.ID, value = "submitInfo")
        address_input = self.driver.find_element(by = By.ID, value = "address")
        name_input = self.driver.find_element(by = By.ID, value = "name")
        value_input = self.driver.find_element(by = By.ID, value = "value")

        address_input.click();  address_input.send_keys("1.1.1.1")
        name_input.click();     name_input.send_keys("Key A")
        value_input.click();    value_input.send_keys("Value A")
        confirm_button.click()

        self.assertNotEqual(self.driver.current_url, self.comparator_url)
        # There is not really a better test for success (especially because selenium doesn't return error codes)
        # but it is a UI test anyway, so all we're doing is making sure the UI works

    def test_removeInformationForm_isFunctional(self):
        confirm_button = self.driver.find_element(by = By.ID, value = "submitRemove")
        address_input = self.driver.find_element(by = By.ID, value = "removeAddress")
        name_input = self.driver.find_element(by = By.ID, value = "removeName")

        address_input.click();  address_input.send_keys("1.1.1.1")
        name_input.click();     name_input.send_keys("Key A")
        confirm_button.click()

        self.assertNotEqual(self.driver.current_url, self.comparator_url)

    def test_getInformationForm_isFunctional(self):
        confirm_button = self.driver.find_element(by = By.ID, value = "submitGetEntry")
        address_input = self.driver.find_element(by = By.ID, value = "getAddress")

        address_input.click();  address_input.send_keys("1.1.1.1")
        confirm_button.click()

        self.assertNotEqual(self.driver.current_url, self.comparator_url)

        # Unfortunately, when reading the body in selenium it contains HTML-tags inserted by the browser.
        # Here, we filter out the HTML tags to create raw, parsable JSON for verification
        content_page : str = self.driver.page_source
        content_filtered = "{" + "{".join(content_page.split("{")[1:])
        content_filtered = "}".join(content_filtered.split("}")[:-1]) + "}"
        content = json.loads(content_filtered)
        
        self.assertTrue("fields" in content.keys())
        self.assertEqual("1.1.1.1", content["ip"])

if __name__ == "__main__":
    unittest.main()
