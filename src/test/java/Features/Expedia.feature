@test
Feature: As an user , I should be able to search for flights + accommodation for destination city and with traveler data

  Background:
    Given  Browser is opened and user is navigated to website with url "https://www.expedia.com/" with page title "Expedia Travel: Vacation Homes, Hotels, Car Rentals, Flights & More"
    And "United States" version of website is open

  Scenario: verify user is able to search for flight with accommodation
    When user choose destination city as " New York" with code "JFK"
    And user selects add flight option
    And user choose origin city as " Brussels" with code "BRU"
    And user selects Checkin date as "28-April 2021"
    And user selects traveler as "1" adult and "1" child of age "3"
    And user clicks on search button
    Then result page contains options with "New york" as destination





