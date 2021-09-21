package com.dior.steps.test;

import com.dior.context.ScenarioContext;
import com.dior.pageObjects.AccountPage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class AccountStep implements En {

    public AccountStep(
            AccountPage accountPage,
            ScenarioContext scenario
    ){

        Given("User is on account login version page", () -> {
            accountPage.navigateToAccountLogin();
        });

        When("User get login password size requirement", () -> {

            accountPage.getLoginPasswordFieldSize();
        });

        When("User move on account inscription version page", () -> {
            accountPage.navigateToAccountInscription();
        });

        When("User get inscription password size requirement", () -> {
            accountPage.getInscriptionPasswordFieldSize();
        });

        Then("User should have save size requirement", () -> {
            accountPage.verifyPasswordFieldSize();
            accountPage.saveScreenShotPNG();
            Assert.assertEquals(accountPage.verifyPasswordFieldSize(),true);
        });




    }


}
