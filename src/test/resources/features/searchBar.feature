Feature: The user shall be able to search in products in Tesco

  Rule: Searchbar function

    Background:
      Given open main page
      And accept cookies

    @TC_SearchWithResults
    Scenario: Search with results
      Given search bar is on the page
      When search a <product> which exist
      Then it shows the results

    @TC_SearchWithoutResults
    Scenario: Search without results
      Given search bar is on the page
      When search a product which is not exist
      Then it shows not found message