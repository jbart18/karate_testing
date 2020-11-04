@chrome_e2eTest
Feature: chrome browser :: E2E core user journey verification.

  Background:
    * configure driver = { type: 'chromedriver', start: false, webDriverUrl: 'http://localhost:4444/wd/hub'}

  Scenario: Basic website visit
    Given driver 'https://www.news.com.au/'
    * assert exists('.date')
