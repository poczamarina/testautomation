Feature: The user shall be able to search in products in Tesco

  Rule: Searchbar function

    Background:
      Given open main page
      And accept cookies

    @TC_SearchWithResults
    Scenario Outline: Search with results
      Given search bar is on the page
      And the language is "<lang>"
      When search a "<product>" which exist
      Then it shows the results which contains "<product>"
      Examples:
        | product  | lang   |
        | alma    | magyar |
        | liszt   | magyar |

    @TC_SearchWithoutResults
    Scenario Outline: Search without results
      Given search bar is on the page
      And the language is "<lang>"
      When search a "<product>" which is not exist
      Then it shows not found the "<product>" message
      Examples:
        |   product     | lang  |
        | nincsilyen    |magyar |
        | ilyensem      |magyar |