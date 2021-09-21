@Web
Feature: Re-Test Cases for DIOR Website with account Access

  @Bug391
  Scenario: Check that after deconnexion user favoris don't appear
    Given User access to his account
    When User click on favoris
    Then User should see favoris element appear
    And User click get disconnected
    Then User should see favoris element disappear