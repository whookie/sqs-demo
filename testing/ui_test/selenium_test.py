#!/usr/bin/python3
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By

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

    def test_addAddressForm_isFunctional(self):
        confirm_button = self.driver.find_element(by = By.ID, value = "confirmAddAddress")
        address_input = self.driver.find_element(by = By.ID, value = "ipAddress")

        address_input.click()
        address_input.send_keys("1.1.1.1")
        confirm_button.click()

        self.assertNotEqual(self.driver.current_url, self.comparator_url)
        # There is not really a better test for success (especially because selenium doesn't return error codes)
        # but it is a UI test anyway, so all we're doing is making sure the UI works

    def test_addInformationForm_isFunctional(self):
        confirm_button = self.driver.find_element(by = By.ID, value = "confirmAddInfo")
        address_input = self.driver.find_element(by = By.ID, value = "ipAddress2")
        name_input = self.driver.find_element(by = By.ID, value = "name")
        value_input = self.driver.find_element(by = By.ID, value = "value")

        address_input.click(); address_input.send_keys("1.1.1.1")
        name_input.click(); name_input.send_keys("Name")
        value_input.click(); value_input.send_keys("Vaue")
        confirm_button.click()

        self.assertNotEqual(self.driver.current_url, self.comparator_url)

