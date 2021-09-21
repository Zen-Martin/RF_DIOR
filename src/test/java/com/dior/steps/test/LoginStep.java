package com.dior.steps.test;

import com.dior.context.ScenarioContext;
import com.dior.pageObjects.LoginPage;
import io.cucumber.java8.En;
import org.testng.Assert;

public class LoginStep implements En {

    public LoginStep(
            LoginPage loginPage,
            ScenarioContext scenario
    ){

        Given("User access to his account", () -> {
            loginPage.navigateToHomePage_fr();
            loginPage.connexion();
        });

        When("User click on favoris", () -> {
            loginPage.refresh_page();
        });

        Then("User should see favoris element appear", () -> {
            loginPage.getConnectedFavorisElement();
            loginPage.saveScreenShotPNG();
        });

        Then("User click get disconnected", () -> {
            loginPage.getDisconnectedFavorisElement();
            loginPage.saveScreenShotPNG();
        });

        Then("User should see favoris element disappear", () -> {
            loginPage.verifyFavorisElementState();
            loginPage.saveScreenShotPNG();
            Assert.assertEquals(loginPage.verifyFavorisElementState(),true);
        });


    }

}
