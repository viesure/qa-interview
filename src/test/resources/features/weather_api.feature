Feature: Check weather endpoint

  Scenario: Check if api return with all properties
    When Get the data from the weather api
    Then All of the properties are in the response

  Scenario Outline: Update condition id and check the condition field and icon
    When Update the weather api with <conditionId> as condition id
    Then Check if the update was successfully
    And Check if the name matches with <condition>
    And Check if the icon matches with <condition>.png
    Examples:
      | conditionId | condition |
      | 1           | "clear"   |
      | 2           | "windy"   |
      | 3           | "mist"    |
      | 4           | "drizzle" |
      | 5           | "dust"    |

  Scenario: Check the invalid condition id update
    When Update the weather api with 6 as condition id
    Then Check if the api send back an error

  Scenario Outline: Update api with negative Fahrenheit degrees
    When Update the weather api with <tempInFahrenheit> as temperature in Fahrenheit
    Then Check if the update was successfully
    And Check if the value matches with <tempInFahrenheit> in the GET request
    Examples:
      | tempInFahrenheit |
      | -1               |
      | -30              |

  Scenario: Update api with fraction Fahrenheit degrees
    When Update the weather api with 10.98 as temperature in Fahrenheit
    Then Check if the update was successfully
    And Check if the 10.98 was rounded correctly

  Scenario Outline: Check if the Celsius was calculated properly
    When Update the weather api with <tempInFahrenheit> as temperature in Fahrenheit
    Then Check if the update was successfully
    And Check if the Celsius was calculated properly from <tempInFahrenheit>

    Examples:
      | tempInFahrenheit |
      | 5                |
      | 40               |
      | 46               |

  Scenario Outline: Check if the description field was generated properly
    When Update the weather api with <tempInFahrenheit> as temperature in Fahrenheit
    Then Check if the update was successfully
    And Check if the description is the "The weather is <descriptionProperty>

    Examples:
      | tempInFahrenheit | descriptionProperty |
      | 10               | "freezing"          |
      | 32               | "freezing"          |
      | 34               | "cold"              |
      | 49               | "cold"              |
      | 50               | "mild"              |
      | 67               | "mild"              |
      | 68               | "warm"              |
      | 76               | "warm"              |
      | 77               | "hot"               |