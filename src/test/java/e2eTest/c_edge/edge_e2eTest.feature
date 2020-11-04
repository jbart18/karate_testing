Feature: edge browser :: E2E core user journey verification.

  Background:
    * def session = { capabilities: { browserName: "edge" } }
    * configure driver = { type: 'msedgedriver', webDriverSession: '#(session)', start: false, showDriverLog: true, webDriverUrl: 'http://localhost:4444/wd/hub'}

  Scenario: Basic website visit
    Given driver 'https://www.news.com.au/'
    * assert exists('.date')
