@test
Feature: Check Google

  Scenario Outline: check that title contains searched word
    Given open https://www.google.com
    And input to search field text "<request>"
        And press enter
        Then check that first result contains in title request "<request>"
    Examples: This is a test data
      | request           |
      | automation        |
      | selenium          |
      | testautomationday |


  Scenario Outline: check if search result contains expected domain
    Given open https://www.google.com
    And input to search field text "<request>"
    And press enter
    Then check that one of "5" pages results contains "<request>"
    Examples: This is a test data
      | request           |
      | automation        |
      | selenium          |
      | testautomationday |

