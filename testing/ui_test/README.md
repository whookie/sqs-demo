# Selenium UI Test

## Installation
- Install Python Selenium library
```
pip install selenium
```
- Install Google Chrome browser
- Install Selenium chrome driver
    1. https://chromedriver.chromium.org/downloads
    2. Download and upzip
    3. Move file `chromedriver` into the same directory as `selenium_test.py`

## Execution
```
python3 -m unittest selenium_test.py
```
It is required to execute this command from the directory in which `selenium_test.py` is located in.

## Note for WSL users:
- WSL2 is required and the chrome browser must be installed in the linux distribution.
- It must be possible to display interfaces of applications running in WSL2.
