Feature: Check website's search field and search functionality

  Scenario: Check the placeholder's text
    When Open the website
    Then Check if the placeholder's name of the search field is correct
    Then Close the browser

  Scenario Outline: Check search flow and expected Date and Time
    When Open the website
    Then Search for <searchedCity>
    Then Check you arrived to the search result page with <searchedCity> in the field
    And Select where name is <selected>
    Then Verify the name of the city is <selected>
    Then Verify the data and time
    Examples:
      | searchedCity | selected     |
      | "Sydney"     | "Sydney, AU" |

