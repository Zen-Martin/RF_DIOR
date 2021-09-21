@Web
Feature: Re-Test Cases for DIOR Website without account Access

  #------------------- Bug 381 -------------------------------------------------------
  @Bug381
  Scenario: Check that traduction in english is effective
    Given User is on international website homepage
    When User look all headers elements
    Then User should see element *Maison* in english

  @Bug351
  Scenario: Check that frame have same size
    Given User is on website french version
    When User click on conditions vente general
    Then User should see same frame size
    When User refresh the page
    Then User should see same frame size too

  @Bug357
  Scenario: Check that **ask question button** is cliquable
    Given User is on website french version on rubrique **contact-couture**
    When User scroll-down to rubrique ask question
    Then User should be able to click on **ask question**

  @Bug348
  Scenario: Check that text-area provides space with writing zone
    Given User is on website french version on rubrique **contact-couture**
    When User write inside zone area
    Then User should see space between zone and writting

  @Bug392
  Scenario: Check that we can connect on the international website
    Given User is on website french version
    And User should be able to connect
    When User move to international website
    Then User should be also able to connect

  @Bug404
  Scenario: Check that language service header appear everytime
    Given User is on website french version
    When User plays with language version
    Then User should see service header appear

  @Bug395
  Scenario: Check that slider effects is available on dior beauty page
    Given User is on **beauty page**
    When User plays with slider
    Then User should see slider effects

  @Bug385
  Scenario: Check that link aren't same on job page
    Given User is on **job page**
    When User click on **horlogerie**
    Then User should be redirect on another page

  @Bug375
  Scenario: Check that require size for password is the same everywhere
    Given User is on account login version page
    When User get login password size requirement
    And User move on account inscription version page
    And User get inscription password size requirement
    Then User should have save size requirement