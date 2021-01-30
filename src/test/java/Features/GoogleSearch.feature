@test
Feature: As an user , I should be able to make a search on webpage

  Background:
    Given Browser is opened and user is navigated to website with url "https://www.google.com/" with page title "Google"
  Scenario Outline: verify user is able to search for given word on google
    When user try  searching  "<data>" on web page
    Then user should be navigated to search result page with "<pageTitle>"
    Examples:
      | data | pageTitle |
      | Bahamas   | Bahamas - Google Search|
      | Amsterdam | Amsterdam - Google Search |

