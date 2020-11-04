  @firefox_e2eTest
  Feature: firefox browser :: E2E core user journey verification.

    Background:
      * configure driver = { type: 'geckodriver', start: false, webDriverUrl: 'http://localhost:4444/wd/hub'}

    Scenario: Basic website visit
      Given driver 'https://www.news.com.au/'
      * assert exists('.date')
